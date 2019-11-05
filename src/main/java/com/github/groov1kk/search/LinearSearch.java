package com.github.groov1kk.search;

public class LinearSearch<T extends Comparable<T>> implements Search<T> {

  @Override
  public int rank(T[] array, T value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].compareTo(value) == 0) {
        return i;
      }
    }
    return -1;
  }
}
