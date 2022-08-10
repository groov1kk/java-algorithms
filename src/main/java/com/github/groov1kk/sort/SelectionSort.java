package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.Utils.swap;

/**
 * Selection sort algorithm.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n^2).
 */
public class SelectionSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int index = i;

      for (int j = index; j < array.length; j++) {
        if (array[j] < array[index]) {
          index = j;
        }
      }

      if (index != i) {
        swap(array, i, index);
      }
    }
    return array;
  }
}
