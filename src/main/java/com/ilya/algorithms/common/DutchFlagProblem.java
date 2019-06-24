package com.ilya.algorithms.common;

import static com.ilya.algorithms.Utils.swap;

/**
 * <a href="https://en.wikipedia.org/wiki/Dutch_national_flag_problem">Dutch national flag problem
 * algorithm</a>.
 *
 * <p>Applies arrays which consist only of [0, 1, 2] numbers, where
 * <li>
 *     <ul>0 - red color</ul>
 *     <ul>1 - white color</ul>
 *     <ul>2 - blue color</ul>
 * </li>
 */
public class DutchFlagProblem {

  public int[] partition(int[] array) {
    int low = 0;
    int mid = 0;
    int high = array.length - 1;

    while (mid <= high) {
      if (array[mid] == 0) {
        swap(array, low++, mid++);
      } else if (array[mid] == 2) {
        swap(array, mid, high--);
      } else {
        mid++;
      }
    }

    return array;
  }
}
