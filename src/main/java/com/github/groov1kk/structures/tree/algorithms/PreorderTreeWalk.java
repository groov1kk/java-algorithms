package com.github.groov1kk.structures.tree.algorithms;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;

public class PreorderTreeWalk<K, V> implements VisitedTraversal<Node<K, V>> {

  @Override
  public void traverse(@Nullable Node<K, V> node, @Nullable Consumer<Node<K, V>> visitor) {
    if (node == null) {
      return;
    }

    if (visitor != null) {
      visitor.accept(node);
    }

    traverse(node.left(), visitor);
    traverse(node.right(), visitor);
  }
}
