package com.ilya.algorithms.search;

public interface Search<T extends Comparable<T>> {

  /**
   * Searches for the specific {@code value} in a given array of and returns it's index. If value is
   * not present in this array - this method returns {@code -1}.
   *
   * @param array array of comparable values to search value
   * @param value value to search
   * @return an index of searched value or -1 if value is not present
   */
  int rank(T[] array, T value);
}
