package com.github.groov1kk.utils;

import java.util.Objects;
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
    Objects.requireNonNull(array, "Array must not be null");

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
    Objects.requireNonNull(array, "Array must not be null");

    E temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }

  /**
   * Returns an element with the minimum value from the given array.
   *
   * @param array Array to find minimum element
   * @return Minimum element
   */
  public static int min(int[] array) {
    Objects.requireNonNull(array, "Array must not be null");

    int min = array[0];

    for (int i = 1; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    return min;
  }

  /**
   * Returns an element with the maximum value from the given array.
   *
   * @param array Array to find maximum element
   * @return Maximum element
   */
  public static int max(int[] array) {
    Objects.requireNonNull(array, "Array must not be null");

    int max = array[0];

    for (int i = 1; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  /**
   * Shuffles elements in a given array in random order.
   *
   * @param array Array to shuffle elements
   */
  public static int[] shuffle(int[] array) {
    Objects.requireNonNull(array, "Array must not be null");

    Random random = ThreadLocalRandom.current();
    for (int i = 0; i < array.length; i++) {
      swap(array, i, random.nextInt(i + 1));
    }
    return array;
  }

  /**
   * Converts integer varargs into integer array.
   *
   * @param values integer values to convert
   * @return int array
   */
  public static int[] intArray(int... values) {
    return values;
  }
}
