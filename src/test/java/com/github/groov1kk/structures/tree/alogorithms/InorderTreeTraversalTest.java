package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.InorderTreeTraversal;
import com.github.groov1kk.structures.tree.algorithms.Node;

public class InorderTreeTraversalTest extends BaseTreeAlgorithmsTest {

  private final InorderTreeTraversal<Integer, String> inorderTreeTraversal = new InorderTreeTraversal<>();

  @Test
  void testInorderTreeWalkKeys() {
    Node<Integer, String> node = getTree();

    Queue<Integer> keysResult = new ArrayQueue<>();
    inorderTreeTraversal.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(4, 2, 5, 1, 3));
  }

  @Test
  void testInorderTreeWalkValues() {
    Node<Integer, String> node = getTree();

    Queue<String> valuesResult = new ArrayQueue<>();
    inorderTreeTraversal.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("four", "two", "five", "one", "three"));
  }
}
