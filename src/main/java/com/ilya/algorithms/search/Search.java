package com.ilya.algorithms.search;

public interface Search {

  /**
   * Searches for the specific {@code value} in a given array of integers and returns it's index. If
   * values is not present in this array - this method returns {@code -1}.
   *
   * @param array array of integers to search value
   * @param value value to search
   * @return an index of searched value or -1 if value is not present
   */
  int rank(int[] array, int value);
}
