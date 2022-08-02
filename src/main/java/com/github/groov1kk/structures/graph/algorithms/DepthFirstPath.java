package com.github.groov1kk.structures.graph.algorithms;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.graph.Graph;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

public final class DepthFirstPath<V> {

  private final Map<V, V> edgeTo = new HashMap<>();

  private final BiPredicate<V, V> strategy;
  private final V either;
  private final DepthFirstSearch<V> depthFirstSearch;

  /**
   * Creates an instance of depth first path algorithm.
   *
   * @param graph Graph to check the paths
   * @param either Starting graph's node
   * @param strategy Nodes comparison strategy
   */
  public DepthFirstPath(Graph<V> graph, V either, BiPredicate<V, V> strategy) {
    this.either = either;
    this.strategy = requireNonNull(strategy);
    this.depthFirstSearch = requireNonNull(graph).depthFirstSearch(either, new PathVisitor(graph));
  }

  /**
   * Checks, whether the {@link #either} and {@code other} vertices are connected, and they have a
   * path between them.
   *
   * @param other The second vertex
   * @return Is the path between {@link #either} and {@code other} exist.
   */
  public boolean hasPath(V other) {
    return depthFirstSearch.isVisited(other);
  }

  /**
   * Returns a path (list of nodes) between the {@link #either} and {@code other} nodes of the given
   * graph. If there is no connection between these two nodes, this method returns {@code null}. To
   * determine the connection and constructing the path this method uses DFS algorithm.
   *
   * @param other The second vertex
   * @return List of nodes between the two given vertices
   * @see DepthFirstSearch#traverse(Object, Consumer)
   */
  @Nullable
  public Iterable<V> getPath(V other) {
    if (!hasPath(other)) {
      return null;
    }

    Stack<V> result = new LinkedStack<>();
    for (V x = other; !strategy.test(x, either); x = edgeTo.get(x)) {
      result.push(x);
    }
    result.push(either);
    return result;
  }

  /** Visitor that makes a path between the current vertex and its adjacent nodes. */
  private class PathVisitor implements Consumer<V> {

    private final Graph<V> graph;

    PathVisitor(Graph<V> graph) {
      this.graph = graph;
    }

    @Override
    public void accept(V vertex) {
      for (V adj : graph.adjacent(vertex)) {
        if (!edgeTo.containsKey(adj) && !strategy.test(adj, vertex)) {
          edgeTo.put(adj, vertex);
        }
      }
    }
  }
}
