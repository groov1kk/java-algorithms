package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.Utils.swap;

/**
 * Shell sort algorithm. Uses Pratt's and Knuth's gap sequence.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n^(3/2)).
 */
public class ShellSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int size = array.length;

    int h = 1;
    while (h < size / 3) {
      h = h * 3 + 1;
    }

    while (h > 0) {
      insertionSort(array, h, size);
      h = h / 3;
    }
    return array;
  }

  private void insertionSort(int[] array, int from, int to) {
    for (int i = from; i < to; i++) {
      int index = i;
      while (index >= from && array[index - from] > array[index]) {
        swap(array, index, index - from);
        index = index - from;
      }
    }
  }
}
