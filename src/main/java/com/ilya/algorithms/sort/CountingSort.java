package com.ilya.algorithms.sort;

import com.ilya.algorithms.Utils;

/**
 * Counting sort algorithm. Stable implementation. Requires additional auxiliary array.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n+m). Memory consumption is O(n + m).
 */
public class CountingSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int min = Utils.min(array);
    int max = Utils.max(array);

    int[] counts = new int[max - min + 1];

    for (int element : array) {
      counts[element - min]++;
    }

    for (int i = 1; i < counts.length; i++) {
      counts[i] = counts[i] + counts[i - 1];
    }

    int[] aux = new int[array.length];
    for (int i = array.length - 1; i >= 0; i--) {
      aux[--counts[array[i] - min]] = array[i];
    }

    System.arraycopy(aux, 0, array, 0, array.length);
    return array;
  }
}
