package com.ilya.algorithms.sort;

import com.ilya.algorithms.common.DutchFlag;

/**
 * Quick sort algorithm. Uses Dutch National Flag problem for array partition.
 *
 * <p>This implementation works faster than {@link QuickSort} when a given array contains a lot of
 * repeated elements.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n*log(n)).
 */
public class QuickSort3WayPartition implements Sort {

  @Override
  public int[] sort(int[] array) {
    return sort(array, 0, array.length - 1);
  }

  private int[] sort(int[] array, int left, int right) {
    if (left >= right) {
      return array;
    }

    DutchFlag flag = DutchFlag.rearrange(array, array[left], left, right);

    sort(array, left, flag.lowBorder());
    sort(array, flag.highBorder(), right);
    return array;
  }
}
