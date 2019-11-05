package com.github.groov1kk.structures.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MaxBinaryHeapTest {

  @Test
  public void testMaxBinaryHeap() {
    MaxBinaryHeap<Integer> heap = new MaxBinaryHeap<>();
    heap.add(1);
    heap.add(3);
    heap.add(2);
    heap.add(4);
    heap.add(5);

    Assert.assertThat(heap.delMax(), is(5));
    Assert.assertThat(heap.delMax(), is(4));
  }

  @Test
  public void testMaxBinaryHeapFromArray() {
    Integer[] array = new Integer[] {4, 1, 5, 3, 2, -1, 2};
    MaxBinaryHeap<Integer> heap = new MaxBinaryHeap<>(array);

    Assert.assertThat(heap.delMax(), is(5));
  }
}
