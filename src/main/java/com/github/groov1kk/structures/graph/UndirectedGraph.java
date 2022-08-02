package com.github.groov1kk.structures.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.github.groov1kk.structures.graph.algorithms.DepthFirstSearch;

/**
 * Undirected graph implementation. Does not apply {@code null} elements. Supports cycles and
 * parallel edges.
 *
 * <p>Notice, that {@link #addEdge(Object, Object)} method creates additional vertexes, if they
 * haven't been in this graph before invocation.
 *
 * @param <V> Vertexes type
 */
@NotThreadSafe
public class UndirectedGraph<V> implements Graph<V> {

  private final Map<V, List<V>> adjacent = new HashMap<>();

  private int edges;

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

    // Cycles aware
    if (!either.equals(other)) {
      this.adjacent.get(other).add(either);
    }
    this.edges++;
  }

  @Override
  public void removeVertex(@Nullable V vertex) {
    if (vertex == null) {
      return;
    }

    for (V adjacentVertex : adjacent(vertex)) {
      List<V> adjacent = this.adjacent.get(adjacentVertex);
      while (adjacent.remove(vertex)) {
        this.edges--;
      }
    }

    this.adjacent.remove(vertex);
  }

  @Override
  public void removeEdge(V either, V other) {
    if (isAdjacent(either, other)) {
      this.adjacent.get(either).remove(other);
      this.adjacent.get(other).remove(either);
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

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");
    DepthFirstSearch<V> search = new DepthFirstSearch<>(this);
    for (V next : this) {
      if (!search.isVisited(next)) {
        if (result.length() > 1) {
          result.append(",").append(" ");
        }
        StringBuilder builder = new StringBuilder("[");
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
    }
    return result.append("]").toString();
  }

  @Nonnull
  @Override
  public Iterator<V> iterator() {
    return new Iterator<>() {

      private final Iterator<V> iterator = UndirectedGraph.this.adjacent.keySet().iterator();

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
   * Undirected graph builder.
   *
   * @param <V> Vertexes type
   */
  public static class Builder<V> {

    private final UndirectedGraph<V> graph = new UndirectedGraph<>();

    public Builder<V> addVertex(V vertex) {
      graph.addVertex(vertex);
      return this;
    }

    public Builder<V> addEdge(V either, V other) {
      graph.addEdge(either, other);
      return this;
    }

    public UndirectedGraph<V> build() {
      return graph;
    }
  }
}
