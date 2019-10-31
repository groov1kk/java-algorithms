package com.ilya.algorithms.sort;

import static com.ilya.algorithms.Utils.swap;

/**
 * Shaker sort algorithm.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n^2).
 */
public class ShakerSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int start = 0;
    int end = array.length - 1;

    while (start <= end) {
      for (int i = start; i < end; i++) {
        if (array[i] > array[i + 1]) {
          swap(array, i, i + 1);
        }
      }
      end--;

      for (int i = end; i > start; i--) {
        if (array[i] < array[i - 1]) {
          swap(array, i, i - 1);
        }
      }
      start++;
    }
    return array;
  }
}
