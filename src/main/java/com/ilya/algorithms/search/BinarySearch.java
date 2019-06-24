package com.ilya.algorithms.search;

public class BinarySearch implements Search {

  @Override
  public int rank(int[] array, int value) {
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      int middle = low + (high - low) / 2;

      if (value == array[middle]) {
        return middle;
      } else if (value < array[middle]) {
        high = middle - 1;
      } else {
        low = middle + 1;
      }
    }
    return -1;
  }
}
