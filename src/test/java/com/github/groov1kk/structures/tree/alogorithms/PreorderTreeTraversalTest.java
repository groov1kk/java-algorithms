package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.Node;
import com.github.groov1kk.structures.tree.algorithms.PreorderTreeTraversal;

public class PreorderTreeTraversalTest extends BaseTreeAlgorithmsTest {

  private final PreorderTreeTraversal<Integer, String> preorderTreeTraversal = new PreorderTreeTraversal<>();

  @Test
  void testPreorderTreeWalkKeys() {
    Node<Integer, String> node = getTree();

    Queue<Integer> keysResult = new ArrayQueue<>();
    preorderTreeTraversal.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(1, 2, 4, 5, 3));
  }

  @Test
  void testPreorderTreeWalkValues() {
    Node<Integer, String> node = getTree();

    Queue<String> valuesResult = new ArrayQueue<>();
    preorderTreeTraversal.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("one", "two", "four", "five", "three"));
  }
}
