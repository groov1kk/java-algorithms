package com.ilya.algorithms.sort;

import com.ilya.algorithms.Utils;

/**
 * Radix sort algorithm. Uses LSD implementation. Applies both positive and negative decimal
 * integers.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n*m).
 */
public class RadixSort implements Sort {

  /** Default decimal range. */
  private static final int RADIX = 10;

  @Override
  public int[] sort(int[] array) {
    int max = Utils.max(array);
    int min = Utils.min(array);

    int[] aux = new int[array.length];

    int exponent = 1;
    while ((max - min) / exponent > 0) {
      countingSort(array, aux, exponent, min, max);
      exponent = exponent * RADIX;
    }
    return array;
  }

  private void countingSort(int[] array, int[] aux, int exponent, int min, int max) {
    int[] counts = new int[RADIX];

    for (int element : array) {
      counts[((element - min) / exponent) % RADIX]++;
    }

    for (int i = 1; i < counts.length; i++) {
      counts[i] = counts[i] + counts[i - 1];
    }

    for (int i = array.length - 1; i >= 0; i--) {
      aux[--counts[((array[i] - min) / exponent) % RADIX]] = array[i];
    }

    System.arraycopy(aux, 0, array, 0, array.length);
  }
}