package com.ilya.algorithms.structures.tree;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static com.ilya.algorithms.Utils.swap;

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

  @Nullable
  public E min() {
    return cursor == 0 ? null : array[0];
  }

  @Nullable
  public E delMin() {
    if (cursor == 0) {
      return null;
    }

    E min = array[0];
    swap(array, 0, --cursor);
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
      swap(array, index, min);
      heapify(min, size);
    }
  }

  @Override
  protected final void increaseKey(int index) {
    while (index > 0 && array[index / 2].compareTo(array[index]) > 0) {
      swap(array, index, index / 2);
      index = index / 2;
    }
  }
}
