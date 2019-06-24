package com.ilya.algorithms.common;

public final class KadanesAlgorithm {

  public int maxSubArray(int[] array) {
    int maxEndingHere = array[0];
    int maxSoFar = array[0];

    for (int i = 1; i < array.length; i++) {
      maxEndingHere = Math.max(array[i], array[i] + maxEndingHere);
      maxSoFar = Math.max(maxEndingHere, maxSoFar);
    }
    return maxSoFar;
  }
}
