package com.github.groov1kk.structures.graph.algorithms;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;
import com.github.groov1kk.structures.graph.Graph;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

/**
 * Depth First Search algorithm implementation. Uses iterative traverse of graphs nodes.
 *
 * @param <V> Vertexes type
 */
public final class DepthFirstSearch<V> implements VisitedTraversal<V> {

  private final Set<V> visited = new HashSet<>();
  private final Graph<V> graph;

  public DepthFirstSearch(Graph<V> graph) {
    this.graph = Objects.requireNonNull(graph);
  }

  /**
   * Checks, whether the given {@code node} has been visited during the performing of DFS.
   *
   * @param node Graph's node to check
   * @return Is node visited
   * @see #traverse(Object, Consumer)
   */
  @Override
  public boolean isVisited(V node) {
    return visited.contains(node);
  }

  /**
   * Iterative DFS algorithm.
   *
   * @param node Graph's node to start search
   * @param visitor Algorithm that would be applied for every graph's node during the search
   */
  @Override
  public void traverse(V node, @Nullable Consumer<V> visitor) {
    Stack<V> stack = new LinkedStack<>();
    stack.push(node);

    while (!stack.isEmpty()) {
      V vertex = stack.pop();
      if (isVisited(vertex)) {
        continue;
      }

      visited.add(vertex);
      if (visitor != null) {
        visitor.accept(vertex);
      }

      for (V adj : graph.adjacent(vertex)) {
        if (!isVisited(adj)) {
          stack.push(adj);
        }
      }
    }
  }
}
