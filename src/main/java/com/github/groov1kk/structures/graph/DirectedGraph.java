package com.github.groov1kk.structures.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.github.groov1kk.structures.graph.algorithms.DepthFirstSearch;
import com.github.groov1kk.structures.graph.algorithms.DirectedCycle;
import com.github.groov1kk.structures.graph.algorithms.TopologicalSort;

/**
 * Directed graph implementation. Does not apply {@code null} elements. It supports cycles and parallel
 * edges.
 *
 * <p>Notice, that {@link #addEdge(Object, Object)} method creates additional vertexes, if they
 * haven't been in this graph before invocation.
 *
 * @param <V> Vertexes type
 */
@NotThreadSafe
public class DirectedGraph<V> implements Graph<V> {

  private final Map<V, List<V>> adjacent;
  private final BiPredicate<V, V> strategy;

  private int edges;

  public DirectedGraph() {
    this(Objects::equals);
  }

  public DirectedGraph(BiPredicate<V, V> strategy) {
    this.strategy = Objects.requireNonNull(strategy);
    this.adjacent = new HashMap<>();
  }

  public DirectedGraph(DirectedGraph<V> graph) {
    Objects.requireNonNull(graph, "Graph must not be null");
    this.adjacent = new HashMap<>(graph.adjacent);
    this.strategy = graph.strategy;
    this.edges = graph.edges;
  }

  @Override
  public final void addVertex(V vertex) {
    Objects.requireNonNull(vertex, "Vertex must not be null");
    this.adjacent.computeIfAbsent(vertex, key -> new LinkedList<>());
  }

  @Override
  public void addEdge(V either, V other) {
    Objects.requireNonNull(either, "Either vertex must not be null");
    Objects.requireNonNull(other, "Other vertex must not be null");

    addVertex(either);
    addVertex(other);

    this.adjacent.get(either).add(other);
    this.edges++;
  }

  @Override
  public void removeVertex(V vertex) {
    if (vertex == null) {
      return;
    }

    // Walks through all vertexes
    for (V v : this.adjacent.keySet()) {
      if (isAdjacent(v, vertex)) {
        List<V> adjacent = this.adjacent.get(v);
        while (adjacent.remove(vertex)) {
          this.edges--;
        }
      }
    }

    this.edges = this.edges - this.adjacent.get(vertex).size();
    this.adjacent.remove(vertex);
  }

  @Override
  public void removeEdge(V either, V other) {
    if (isAdjacent(either, other)) {
      this.adjacent.get(either).remove(other);
      this.edges--;
    }
  }

  @Override
  public Iterable<V> adjacent(V vertex) {
    return this.adjacent.get(vertex);
  }

  @Override
  public final boolean isAdjacent(V either, V other) {
    return this.adjacent.containsKey(either) && this.adjacent.get(either).contains(other);
  }

  @Override
  public int vertices() {
    return this.adjacent.keySet().size();
  }

  @Override
  public int edges() {
    return this.edges;
  }

  /**
   * Determines, whether the graph contains at least one cycle.
   *
   * <p>This method uses DFS algorithm with additional memory to store all the traversed vertexes.
   *
   * @return is graph contain cycle
   */
  public boolean hasCycles() {
    return new DirectedCycle<>(this, strategy).hasCycles();
  }

  /**
   * Determines, whether the graph contains at least one cycle, starting from the given node. If
   * this node relates to some disconnected part of the graph and this part does not contain any
   * cycles, this method will return {@code false}.
   *
   * <p>This method uses DFS algorithm with additional memory to store all the traversed vertexes.
   *
   * @param node Graph's node from which cycle searching algorithm will start
   * @return is graph contain cycle
   */
  public boolean hasCycles(V node) {
    return new DirectedCycle<>(this, node, strategy).hasCycles();
  }

  /**
   * Returns all existing cycles from the graph as a set of vertexes which form these cycles.
   *
   * @return all cycles from the graph
   */
  public Iterable<Iterable<V>> getCycles() {
    return new DirectedCycle<>(this, strategy).getCycles();
  }

  /**
   * Returns all existing cycles from the graph as a set of vertexes which form these cycles,
   * starting from the specific node. If the graph consists of multiple disconnected parts - this
   * method returns only those cycles, which present on the sub-graph which the given node belongs.
   *
   * @param node Graph's node to start search cycles for
   * @return all cycles from the graph
   */
  public Iterable<Iterable<V>> getCycles(V node) {
    return new DirectedCycle<>(this, node, strategy).getCycles();
  }

  /**
   * Performs a topological sort for the all nodes of the graph. If at least one cycle is present in
   * the graph - this method returns {@code null}.
   *
   * @return a list of nodes of the graph in a topological order
   */
  @Nullable
  public Iterable<V> topologicalSort() {
    return new TopologicalSort<>(this).sorted();
  }

  /**
   * Performs a topological sort for the graph, starting from the specific node. If the graph
   * consists of multiple disconnected parts, this method sorts only that part which the given node
   * belongs.
   *
   * @param node Graph's node from which topological sort would start
   * @return a list of nodes of the graph in a topological order
   */
  @Nullable
  public Iterable<V> topologicalSort(V node) {
    return new TopologicalSort<>(this, node).sorted();
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");
    for (V next : this) {
      if (result.length() > 1) {
        result.append(",").append(" ");
      }
      StringBuilder builder = new StringBuilder("[");
      DepthFirstSearch<V> search = new DepthFirstSearch<>(this);
      search.traverse(
          next,
          vertex -> {
            if (builder.length() > 1) {
              builder.append(",").append(" ");
            }
            builder.append(vertex.toString());
          });
      result.append(builder.append("]"));
    }
    return result.append("]").toString();
  }

  @Nonnull
  @Override
  public Iterator<V> iterator() {
    return new Iterator<>() {

      private final Iterator<V> iterator = DirectedGraph.this.adjacent.keySet().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public V next() {
        return iterator.next();
      }
    };
  }

  /**
   * Directed graph builder.
   *
   * @param <V> Vertexes type
   */
  public static class Builder<V> {

    private final DirectedGraph<V> graph;

    public Builder() {
      graph = new DirectedGraph<>();
    }

    public Builder(BiPredicate<V, V> strategy) {
      graph = new DirectedGraph<>(strategy);
    }

    public DirectedGraph.Builder<V> addVertex(V vertex) {
      graph.addVertex(vertex);
      return this;
    }

    public DirectedGraph.Builder<V> addEdge(V either, V other) {
      graph.addEdge(either, other);
      return this;
    }

    public DirectedGraph<V> build() {
      return new DirectedGraph<>(graph);
    }
  }
}
