package com.ilya.algorithms.sort;

/** Strategy interface for sorting algorithms for integer arrays. */
public interface Sort {

  /**
   * Sorts an array of integer values.
   *
   * @param array Array to sort
   * @return sorted array
   */
  int[] sort(int[] array);
}
