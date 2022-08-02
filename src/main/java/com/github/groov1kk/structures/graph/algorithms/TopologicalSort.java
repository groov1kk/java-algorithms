package com.github.groov1kk.structures.graph.algorithms;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.Traversal;
import com.github.groov1kk.structures.graph.DirectedGraph;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

public final class TopologicalSort<V> {

  private final Stack<V> sorted = new LinkedStack<>();

  /**
   * Creates an instance of topological sort algorithm.
   *
   * @param graph Graph, for which topological sort algorithm would be applied
   */
  public TopologicalSort(DirectedGraph<V> graph) {
    if (!requireNonNull(graph).hasCycles()) {
      Traversal<V> topologicalSortSearch = new TopologicalSortSearch(graph);
      for (V node : graph) {
        if (!topologicalSortSearch.isVisited(node)) {
          topologicalSortSearch.traverse(node);
        }
      }
    }
  }

  /**
   * Creates an instance of topological sort algorithm for a specific node.
   *
   * @param graph Graph, for which topological sort algorithm would be applied
   * @param node Graph's node for which topological sort would be started
   */
  public TopologicalSort(DirectedGraph<V> graph, V node) {
    if (!requireNonNull(graph).hasCycles(node)) {
      new TopologicalSortSearch(graph).traverse(node);
    }
  }

  /**
   * Checks whether the examined graph is a directed acyclic graph.
   *
   * @return Is the given graph is directed acyclic graph
   */
  public boolean isDag() {
    return !sorted.isEmpty();
  }

  /**
   * Returns a list of graph's nodes in the topological order. If the given graph contains at least
   * 1 cycle - this method will return {@code null}.
   *
   * @return Vertices in a topological order or null
   */
  @Nullable
  public Iterable<V> sorted() {
    return isDag() ? sorted : null;
  }

  /** Iterative DFS algorithm that performs topological search for the given graph. */
  private class TopologicalSortSearch implements Traversal<V> {

    private final Set<V> visited = new HashSet<>();

    private final DirectedGraph<V> graph;

    TopologicalSortSearch(DirectedGraph<V> graph) {
      this.graph = requireNonNull(graph);
    }

    @Override
    public boolean isVisited(V node) {
      return visited.contains(node);
    }

    @Override
    public void traverse(V node) {
      Stack<V> stack = new LinkedStack<>();
      stack.push(node);

      while (!stack.isEmpty()) {
        V vertex = stack.peek();
        if (isVisited(vertex)) {
          sorted.push(vertex);
          stack.pop();
          continue;
        }

        visited.add(vertex);

        for (V adj : graph.adjacent(vertex)) {
          if (!isVisited(adj)) {
            stack.push(adj);
          }
        }
      }
    }
  }
}
