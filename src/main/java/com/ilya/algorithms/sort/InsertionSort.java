package com.ilya.algorithms.sort;

public class InsertionSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int x = array[i];
      int index = i;

      while (index > 0 && array[index - 1] > x) {
        array[index] = array[index - 1];
        index--;
      }
      array[index] = x;
    }
    return array;
  }
}
