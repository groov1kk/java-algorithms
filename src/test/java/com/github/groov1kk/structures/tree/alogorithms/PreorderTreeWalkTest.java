package com.github.groov1kk.structures.tree.alogorithms;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.PreorderTreeWalk;

public class PreorderTreeWalkTest extends BaseTreeAlgorithmsTest {

  @Test
  public void testPreorderTreeWalk() {
    NodeImpl<Integer, String> node = new NodeImpl<>(1, "one");
    node.setLeft(new NodeImpl<>(2, "two"));
    node.setRight(new NodeImpl<>(3, "three"));

    PreorderTreeWalk<Integer, String> preorderTreeWalk = new PreorderTreeWalk<>();

    Queue<Integer> keysResult = new ArrayQueue<>();
    preorderTreeWalk.traverse(node, it -> keysResult.enqueue(it.key()));

    assertThat(keysResult, contains(1, 2, 3));

    ArrayQueue<String> valuesResult = new ArrayQueue<>();
    preorderTreeWalk.traverse(node, it -> valuesResult.enqueue(it.value()));

    assertThat(valuesResult, contains("one", "two", "three"));
  }
}
