package com.github.groov1kk.structures.tree.algorithms;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;
import com.github.groov1kk.structures.queue.LinkedQueue;
import com.github.groov1kk.structures.queue.Queue;

public final class LeverOrderTreeTraversal<K, V> implements VisitedTraversal<Node<K, V>> {

  @Override
  public void traverse(@Nullable Node<K, V> node, @Nullable Consumer<Node<K, V>> visitor) {
    Queue<Node<K, V>> queue = new LinkedQueue<>();
    queue.enqueue(node);

    while (!queue.isEmpty()) {
      Node<K, V> current = queue.dequeue();
      if (current == null) {
        continue;
      }

      if (visitor != null) {
        visitor.accept(current);
      }

      queue.enqueue(current.left());
      queue.enqueue(current.right());
    }
  }
}
