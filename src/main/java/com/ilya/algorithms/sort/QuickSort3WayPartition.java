package com.ilya.algorithms.sort;

import static com.ilya.algorithms.Utils.swap;

public class QuickSort3WayPartition implements Sort {

  @Override
  public int[] sort(int[] array) {
    return sort(array, 0, array.length - 1);
  }

  private int[] sort(int[] array, int left, int right) {
    if (left >= right) {
      return array;
    }

    int low = left;
    int mid = left;
    int high = right;

    int pivot = array[left];

    // Dutch national flag problem
    while (mid <= high) {
      if (array[mid] < pivot) {
        swap(array, low++, mid++);
      } else if (array[mid] > pivot) {
        swap(array, mid, high--);
      } else {
        mid++;
      }
    }

    sort(array, left, low - 1);
    sort(array, high + 1, right);
    return array;
  }
}
