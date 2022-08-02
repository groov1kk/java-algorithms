package com.github.groov1kk.structures.graph.algorithms;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;
import com.github.groov1kk.structures.graph.Graph;
import com.github.groov1kk.structures.queue.LinkedQueue;
import com.github.groov1kk.structures.queue.Queue;

public final class BreadthFirstSearch<V> implements VisitedTraversal<V> {

  private final Set<V> visited = new HashSet<>();
  private final Graph<V> graph;

  /**
   * Creates an instance of BFS algorithm.
   *
   * @param graph Graph for which BFS should be performed
   */
  public BreadthFirstSearch(Graph<V> graph) {
    this.graph = Objects.requireNonNull(graph);
  }

  /**
   * Checks, whether specific node has been visited during the search.
   *
   * @param node Graph's node to check
   * @return {@code true} if node was visited
   */
  @Override
  public boolean isVisited(V node) {
    return this.visited.contains(node);
  }

  /**
   * Executes BFS algorithm starting from a specific node.
   *
   * @param node Graph's node to start search from
   * @param visitor Algorithm that would be applied for every node during the search
   */
  @Override
  public void traverse(V node, @Nullable Consumer<V> visitor) {
    Queue<V> queue = new LinkedQueue<>();
    queue.enqueue(node);

    while (!queue.isEmpty()) {
      V vertex = queue.dequeue();
      if (isVisited(vertex)) {
        continue;
      }

      visited.add(vertex);

      if (visitor != null) {
        visitor.accept(vertex);
      }

      for (V adj : graph.adjacent(vertex)) {
        if (!isVisited(adj)) {
          queue.enqueue(adj);
        }
      }
    }
  }
}
