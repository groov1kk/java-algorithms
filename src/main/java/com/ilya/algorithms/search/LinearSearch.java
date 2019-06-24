package com.ilya.algorithms.search;

public class LinearSearch implements Search {

  @Override
  public int rank(int[] array, int value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value) {
        return i;
      }
    }
    return -1;
  }
}
