package com.github.groov1kk.structures.tree;

import com.github.groov1kk.Utils;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MinBinaryHeap<E extends Comparable<E>> extends BinaryHeap<E> {

  public MinBinaryHeap() {
    super();
  }

  public MinBinaryHeap(int capacity) {
    super(capacity);
  }

  public MinBinaryHeap(E[] array) {
    super(array);
  }

  /**
   * Returns min element of this binary heap. Time complexity is O(1).
   *
   * @return Min element of this heap.
   */
  @Nullable
  public E min() {
    return cursor == 0 ? null : array[0];
  }

  /**
   * Returns min element from this binary heap and removes it. After removing operation this method
   * restores binary heap invariant and resize array. Time complexity of this operation is O(n).
   *
   * @return Min element of this heap.
   */
  @Nullable
  public E delMin() {
    if (cursor == 0) {
      return null;
    }

    E min = array[0];
    Utils.swap(array, 0, --cursor);
    array[cursor] = null;
    heapify(0, cursor - 1);

    if (cursor != 1 && cursor < array.length / 4) {
      resize(cursor / 2);
    }
    return min;
  }

  @Override
  protected final void heapify(int index, int size) {
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;

    int min = index;
    if (leftChild <= size && array[leftChild].compareTo(array[min]) < 0) {
      min = leftChild;
    }

    if (rightChild <= size && array[rightChild].compareTo(array[min]) < 0) {
      min = rightChild;
    }

    if (min != index) {
      Utils.swap(array, index, min);
      heapify(min, size);
    }
  }

  @Override
  protected final void increaseKey(int index) {
    while (index > 0 && array[index / 2].compareTo(array[index]) > 0) {
      Utils.swap(array, index, index / 2);
      index = index / 2;
    }
  }
}
