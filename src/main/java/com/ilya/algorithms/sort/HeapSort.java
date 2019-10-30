package com.ilya.algorithms.sort;

import static com.ilya.algorithms.Utils.swap;

/**
 * Heap sort algorithm.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n*log(n)).
 */
public class HeapSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int size = array.length - 1;
    buildMaxHeap(array, size);
    while (size > 0) {
      swap(array, 0, size--);
      heapifyMax(array, 0, size);
    }
    return array;
  }

  private void buildMaxHeap(int[] array, int size) {
    for (int i = (int) (Math.round((double) size / 2) - 1); i >= 0; i--) {
      heapifyMax(array, i, size);
    }
  }

  private void heapifyMax(int[] array, int index, int size) {
    int leftChild = index * 2 + 1;
    int rightChild = index * 2 + 2;

    int largest = index;

    if (leftChild <= size && array[leftChild] > array[largest]) {
      largest = leftChild;
    }

    if (rightChild <= size && array[rightChild] > array[largest]) {
      largest = rightChild;
    }

    if (largest != index) {
      swap(array, index, largest);
      heapifyMax(array, largest, size);
    }
  }
}
