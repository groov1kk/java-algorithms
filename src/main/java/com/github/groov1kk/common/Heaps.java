package com.github.groov1kk.common;

import static com.github.groov1kk.utils.Utils.swap;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the <a href="https://en.wikipedia.org/wiki/Heap%27s_algorithm">Heap's</a>
 * algorithm which generates all possible permutations of a given array.
 */
public final class Heaps {

  private Heaps() {}

  /**
   * Generates all possible permutations of the given {@code array}.
   *
   * @param array array which permutations must be generated
   * @return all possible permutations of the given array
   */
  public static List<int[]> generate(int[] array) {
    List<int[]> result = new ArrayList<>();
    rearrange(result, array, array.length);
    return result;
  }

  private static void rearrange(List<int[]> result, int[] array, int k) {
    if (k == 1) {
      result.add(array.clone());
      return;
    }

    rearrange(result, array, k - 1);
    for (int i = 0; i < k - 1; i++) {
      if (k % 2 == 0) {
        swap(array, i, k - 1);
      } else {
        swap(array, 0, k - 1);
      }
      rearrange(result, array, k - 1);
    }
  }
}
