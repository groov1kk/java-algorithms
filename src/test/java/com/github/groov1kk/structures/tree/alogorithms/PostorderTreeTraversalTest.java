package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.Node;
import com.github.groov1kk.structures.tree.algorithms.PostorderTreeTraversal;

public class PostorderTreeTraversalTest extends BaseTreeAlgorithmsTest {

  private final PostorderTreeTraversal<Integer, String> postorderTreeTraversal = new PostorderTreeTraversal<>();

  @Test
  void testPreorderTreeWalkKeys() {
    Node<Integer, String> node = getTree();

    Queue<Integer> keysResult = new ArrayQueue<>();
    postorderTreeTraversal.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(4, 5, 2, 3, 1));
  }

  @Test
  void testPreorderTreeWalkValues() {
    Node<Integer, String> node = getTree();

    Queue<String> valuesResult = new ArrayQueue<>();
    postorderTreeTraversal.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("four", "five", "two", "three", "one"));
  }
}
