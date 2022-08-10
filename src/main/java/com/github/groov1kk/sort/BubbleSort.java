package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.Utils.swap;

/**
 * Bubble sort algorithm.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n^2).
 */
public class BubbleSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int size = array.length - 1;
    boolean swapped;
    do {
      swapped = false;
      for (int i = 0; i < size; i++) {
        if (array[i] > array[i + 1]) {
          swap(array, i, i + 1);
          swapped = true;
        }
      }
      size--;
    } while (swapped);
    return array;
  }
}
