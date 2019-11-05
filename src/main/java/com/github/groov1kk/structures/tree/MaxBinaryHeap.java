package com.github.groov1kk.structures.tree;

import com.github.groov1kk.Utils;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MaxBinaryHeap<E extends Comparable<E>> extends BinaryHeap<E> {

  public MaxBinaryHeap() {
    super();
  }

  public MaxBinaryHeap(int capacity) {
    super(capacity);
  }

  public MaxBinaryHeap(E[] array) {
    super(array);
  }

  /**
   * Returns max element of this binary heap. Time complexity is O(1).
   *
   * @return Max element of this heap.
   */
  @Nullable
  public E max() {
    return cursor == 0 ? null : array[0];
  }

  /**
   * Returns max element from this binary heap and removes it. After removing operation this method
   * restores binary heap invariant and resize array. Time complexity of this operation is O(n).
   *
   * @return Max element of this heap.
   */
  @Nullable
  public E delMax() {
    if (cursor == 0) {
      return null;
    }

    E max = array[0];
    Utils.swap(array, 0, --cursor);
    array[cursor] = null;
    heapify(0, cursor - 1);

    if (cursor != 1 && cursor < array.length / 4) {
      resize(array.length / 2);
    }
    return max;
  }

  @Override
  protected final void increaseKey(int index) {
    while (index > 0 && array[index / 2].compareTo(array[index]) < 0) {
      Utils.swap(array, index, index / 2);
      index = index / 2;
    }
  }

  @Override
  protected final void heapify(int index, int size) {
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;

    int largest = index;
    if (leftChild <= size && array[largest].compareTo(array[leftChild]) < 0) {
      largest = leftChild;
    }

    if (rightChild <= size && array[largest].compareTo(array[rightChild]) < 0) {
      largest = rightChild;
    }

    if (largest != index) {
      Utils.swap(array, index, largest);
      heapify(largest, size);
    }
  }
}
