package com.github.groov1kk.structures;

import java.util.function.Consumer;

/**
 * Traversal algorithm that provides possibility to apply a specific visitor during the search.
 *
 * @param <N> Nodes type
 */
public interface VisitedTraversal<N> extends Traversal<N> {

  Consumer<?> DEFAULT_VISITOR = x -> {};

  @Override
  @SuppressWarnings("unchecked")
  default void traverse(N node) {
    traverse(node, (Consumer<N>) DEFAULT_VISITOR);
  }

  /**
   * Performs traverse of a specific structure.
   *
   * @param node Structure node to start traverse
   * @param visitor Visitor that may be applied for each node found during the traverse
   */
  void traverse(N node, Consumer<N> visitor);
}
