package com.github.groov1kk.sort;

/**
 * Heap sort algorithm.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n^2).
 */
public class InsertionSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int x = array[i];
      int index = i;

      while (index > 0 && array[index - 1] > x) {
        array[index] = array[index - 1];
        index--;
      }
      array[index] = x;
    }
    return array;
  }
}
