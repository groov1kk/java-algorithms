package com.github.groov1kk.search;

public class BinarySearch<T extends Comparable<T>> implements Search<T> {

  @Override
  public int rank(T[] array, T value) {
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      int middle = low + (high - low) / 2;

      if (array[middle].compareTo(value) == 0) {
        return middle;
      } else if (array[middle].compareTo(value) > 0) {
        high = middle - 1;
      } else {
        low = middle + 1;
      }
    }
    return -1;
  }
}
