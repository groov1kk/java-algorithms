package com.ilya.algorithms.sort;

import com.ilya.algorithms.Utils;

public class CountingSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    int min = Utils.min(array);
    int max = Utils.max(array);

    int[] counts = new int[max - min + 1];

    for (int element : array) {
      counts[element - min]++;
    }

    int k = 0;
    for (int i = 0; i < counts.length; i++) {
      for (int j = 0; j < counts[i]; j++) {
        array[k++] = i + min;
      }
    }
    return array;
  }
}
