package com.github.groov1kk.common;

import com.github.groov1kk.sort.InsertionSort;
import com.github.groov1kk.sort.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://en.wikipedia.org/wiki/3SUM">3-Sum problem algorithm.</a>
 *
 * <p>Time complexity is O(n^2).
 */
public final class ThreeSumAlgorithm {

  private static final Sort DEFAULT_SORT_ALGORITHM = new InsertionSort();

  private final Sort sort;

  public ThreeSumAlgorithm() {
    this(DEFAULT_SORT_ALGORITHM);
  }

  public ThreeSumAlgorithm(Sort sort) {
    this.sort = Objects.requireNonNull(sort, "Sort algorithm must not be null");
  }

  /**
   * Returns a list of arrays, each of them contains three values and their sum is 0.
   *
   * @param input Array of integer values to calculate sums
   * @return List of elements which sum is 0
   */
  public List<int[]> sums(int[] input) {
    this.sort.sort(input);

    List<int[]> result = new LinkedList<>();
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
