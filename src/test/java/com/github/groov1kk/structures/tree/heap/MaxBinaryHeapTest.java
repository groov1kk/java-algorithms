package com.github.groov1kk.structures.tree.heap;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxBinaryHeapTest {

  @Test
  public void testMaxBinaryHeap() {
    MaxBinaryHeap<Integer> heap = new MaxBinaryHeap<>();
    heap.add(1);
    heap.add(3);
    heap.add(2);
    heap.add(4);
    heap.add(5);

    assertThat(heap.delMax(), is(5));
    assertThat(heap.delMax(), is(4));
  }

  @Test
  public void testMaxBinaryHeapFromArray() {
    Integer[] array = new Integer[] {4, 1, 5, 3, 2, -1, 2};
    MaxBinaryHeap<Integer> heap = new MaxBinaryHeap<>(array);

    assertThat(heap.delMax(), is(5));
  }
}
