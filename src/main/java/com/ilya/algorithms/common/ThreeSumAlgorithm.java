package com.ilya.algorithms.common;

import com.ilya.algorithms.sort.InsertionSort;
import com.ilya.algorithms.sort.Sort;

import java.util.ArrayList;
import java.util.List;

public final class ThreeSumAlgorithm {

  private final Sort sort;

  public ThreeSumAlgorithm() {
    this(new InsertionSort());
  }

  public ThreeSumAlgorithm(Sort sort) {
    this.sort = sort;
  }

  public List<int[]> sums(int[] input) {
    this.sort.sort(input);

    List<int[]> result = new ArrayList<>();
    for (int i = 0; i < input.length - 2; i++) {
      int j = i + 1;
      int k = input.length - 1;

      while (j < k) {
        int sum = input[i] + input[j] + input[k];

        if (sum == 0) {
          result.add(new int[] {input[i], input[j], input[k]});
          j++;
          k--;
        } else if (sum > 0) {
          k--;
        } else {
          j++;
        }
      }
    }
    return result;
  }
}
