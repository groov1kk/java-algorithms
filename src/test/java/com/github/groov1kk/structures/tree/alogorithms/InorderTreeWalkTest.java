package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.InorderTreeWalk;

public class InorderTreeWalkTest extends BaseTreeAlgorithmsTest {

  @Test
  public void testInorderTreeWalk() {
    NodeImpl<Integer, String> node = new NodeImpl<>(1, "one");
    node.setLeft(new NodeImpl<>(2, "two"));
    node.setRight(new NodeImpl<>(3, "three"));

    InorderTreeWalk<Integer, String> inorderTreeWalk = new InorderTreeWalk<>();

    Queue<Integer> keysResult = new ArrayQueue<>();
    inorderTreeWalk.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(2, 1, 3));

    ArrayQueue<String> valuesResult = new ArrayQueue<>();
    inorderTreeWalk.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("two", "one", "three"));
  }
}
