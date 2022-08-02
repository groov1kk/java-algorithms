package com.github.groov1kk.structures;

/**
 * Traversal or search algorithm that would be applied to graphs or trees.
 *
 * @param <N> Nodes type
 */
public interface Traversal<N> {

  /**
   * Checks, whether specific node has been visited during the search.
   *
   * @param node Graph's node to check
   * @return {@code true} if node was visited
   */
  boolean isVisited(N node);

  /**
   * Performs traverse of a specific structure.
   *
   * @param node Structure node to start traverse
   */
  void traverse(N node);
}
