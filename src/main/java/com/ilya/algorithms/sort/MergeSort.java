package com.ilya.algorithms.sort;

import java.util.Objects;

public class MergeSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    Objects.requireNonNull(array, "Array must not be null");

    int length = array.length;
    int[] aux = new int[length];
    return sort(array, aux, 0, length - 1);
  }

  private int[] sort(int[] array, int[] aux, int left, int right) {
    if (left >= right) {
      return array;
    }

    int mid = left + (right - left) / 2;
    sort(array, aux, left, mid);
    sort(array, aux, mid + 1, right);
    return merge(array, aux, left, mid, right);
  }

  private int[] merge(int[] array, int[] aux, int left, int mid, int right) {
    System.arraycopy(array, left, aux, left, right - left + 1);

    int i = left;
    int j = mid + 1;

    for (int k = left; k <= right; k++) {
      if (i > mid) {
        array[k] = aux[j++];
      } else if (j > right) {
        array[k] = aux[i++];
      } else if (aux[i] < aux[j]) {
        array[k] = aux[i++];
      } else {
        array[k] = aux[j++];
      }
    }
    return array;
  }
}
