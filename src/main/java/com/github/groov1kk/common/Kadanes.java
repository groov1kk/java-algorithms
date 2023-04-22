package com.github.groov1kk.common;

/**
 * Kadane's algorithm.
 *
 * <p>Solves <a href="https://en.wikipedia.org/wiki/Maximum_subarray_problem">Maximum sub-array
 * problem</a>.
 */
public final class Kadanes {

  private Kadanes() {}

  public static int maxSubArray(int[] array) {
    int maxEndingHere = array[0];
    int maxSoFar = array[0];

    for (int i = 1; i < array.length; i++) {
      maxEndingHere = Math.max(array[i], array[i] + maxEndingHere);
      maxSoFar = Math.max(maxEndingHere, maxSoFar);
    }
    return maxSoFar;
  }
}
