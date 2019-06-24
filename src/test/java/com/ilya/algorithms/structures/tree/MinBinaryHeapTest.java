package com.ilya.algorithms.structures.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

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

    Assert.assertThat(heap.delMin(), is(-1));
    Assert.assertThat(heap.delMin(), is(1));
  }

  @Test
  public void testMinBinaryHeapFromArray() {
    Integer[] array = new Integer[] {4, -1, 2, 1, 4, -5, 2};
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(array);

    Assert.assertThat(heap.delMin(), is(-5));
    Assert.assertThat(heap.delMin(), is(-1));
  }
}
