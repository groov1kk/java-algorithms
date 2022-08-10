package com.github.groov1kk.structures.tree.heap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class MinBinaryHeapTest {

  @Test
  public void testMinBinaryHeapTest() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
    heap.add(1);
    heap.add(3);
    heap.add(2);
    heap.add(2);
    heap.add(5);
    heap.add(-1);

    assertThat(heap.delMin(), is(-1));
    assertThat(heap.delMin(), is(1));
  }

  @Test
  public void testMinBinaryHeapFromArray() {
    Integer[] array = new Integer[] {4, -1, 2, 1, 4, -5, 2};
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(array);

    assertThat(heap.delMin(), is(-5));
    assertThat(heap.delMin(), is(-1));
  }
}
