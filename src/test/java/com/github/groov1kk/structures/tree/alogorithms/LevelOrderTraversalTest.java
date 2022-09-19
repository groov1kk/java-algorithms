package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.LeverOrderTreeTraversal;
import com.github.groov1kk.structures.tree.algorithms.Node;

public class LevelOrderTraversalTest extends BaseTreeAlgorithmsTest {

  private final LeverOrderTreeTraversal<Integer, String> leverOrderTraversal = new LeverOrderTreeTraversal<>();

  @Test
  void testLevelOrderWalkKeys() {
    Node<Integer, String> node = getTree();

    Queue<Integer> keysResult = new ArrayQueue<>();
    leverOrderTraversal.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(1, 2, 3, 4, 5));
  }

  @Test
  void testLevelOrderWalkValues() {
    Node<Integer, String> node = getTree();

    Queue<String> valuesResult = new ArrayQueue<>();
    leverOrderTraversal.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("one", "two", "three", "four", "five"));
  }
}
