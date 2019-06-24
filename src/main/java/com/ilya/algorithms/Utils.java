package com.ilya.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Utils {

  private Utils() {}

  /**
   * Swaps two elements with given indexes in a given array.
   *
   * @param array Array with elements to swap
   * @param left An index of left element to swap in a given array
   * @param right An index of right element to swap in a given array
   */
  public static void swap(int[] array, int left, int right) {
    int temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }

  /**
   * Swaps two elements with given indexes in a given array.
   *
   * @param array Array with elements to swap
   * @param left An index of left element to swap in a given array
   * @param right An index of right element to swap in a given array
   */
  public static <E> void swap(E[] array, int left, int right) {
    E temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }

  /**
   * Verifies whether a given array is sorted in ascending order.
   *
   * @param array Array to check
   * @return Is array sorted in ascending order
   */
  public static boolean isSortedAsc(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns an element with the minimum value from a given array.
   *
   * @param array Array to find minimum element
   * @return Minimum element
   */
  public static int min(int[] array) {
    int min = array[0];

    for (int i = 1; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    return min;
  }

  /**
   * Returns an element with the maximum value from a given array.
   *
   * @param array Array to find maximum element
   * @return Maximum element
   */
  public static int max(int[] array) {
    int max = array[0];

    for (int i = 1; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  /**
   * Returns an array of elements which are not present in array {@code a} and present in array
   * {@code b}.
   *
   * <p>For example, if a = [1, 2, 3] and b = [3, 4, 5], this method should return a new array with
   * [4, 5] elements.
   *
   * @param a Array to check difference
   * @param b Array to check difference
   * @return An array with difference
   */
  public static int[] difference(int[] a, int[] b) {
    int min = Math.min(min(a), min(b));
    int max = Math.max(max(a), max(b));

    int[] counts = new int[max - min + 1];

    for (int i : a) {
      counts[i - min]++;
    }

    for (int i : b) {
      counts[i - min]--;
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] < 0) {
        result.add(i + min);
      }
    }
    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  /**
   * Shuffles elements in a given array in random order.
   *
   * @param array Array to shuffle elements
   */
  public static int[] shuffle(int[] array) {
    Random random = ThreadLocalRandom.current();
    for (int i = 0; i < array.length; i++) {
      swap(array, i, random.nextInt(i + 1));
    }
    return array;
  }
}
