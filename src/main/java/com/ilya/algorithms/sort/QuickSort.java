package com.ilya.algorithms.sort;

import static com.ilya.algorithms.Utils.swap;

public class QuickSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    return sort(array, 0, array.length - 1);
  }

  private int[] sort(int[] array, int left, int right) {
    if (left < right) {
      int index = partition(array, left, right);
      sort(array, left, index);
      sort(array, index + 1, right);
    }
    return array;
  }

  private int partition(int[] array, int left, int right) {
    int pivot = array[left + (right - left) / 2];

    while (true) {
      while (array[left] < pivot) {
        left++;
      }

      while (array[right] > pivot) {
        right--;
      }

      if (left >= right) {
        return right;
      }
      swap(array, left, right);
      left++;
      right--;
    }
  }
}
