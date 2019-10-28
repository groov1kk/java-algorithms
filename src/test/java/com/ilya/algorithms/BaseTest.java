package com.ilya.algorithms;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseTest {

  /**
   * Generates an array of integer values with a given length and specific Min and Max elements.
   *
   * @param length length of generated array
   * @param from Min element of array
   * @param to Max element of array
   * @return array of integer values
   * @throws IllegalArgumentException if from > to
   */
  protected int[] array(int length, int from, int to) {
    return ThreadLocalRandom.current().ints(from, to).limit(length).toArray();
  }

  /**
   * Generates an array of <b>distinct</b> integer values with a given length and specific Min and
   * Max elements.
   *
   * @param length length of generated array
   * @param from Min element of array
   * @param to Max element of array
   * @return array of integer values
   * @throws IllegalArgumentException if {@code from > to} and if a length of given array is bigger
   *     than the sum of absolute value of from and to.
   */
  protected Integer[] distinctArray(int length, int from, int to) {
    if (length > Math.abs(from) + Math.abs(to)) {
      throw new IllegalArgumentException(
          "The length of array must not be bigger that the sum of absolute values of 'from' and 'to' elements");
    }

    return ThreadLocalRandom.current()
        .ints(from, to)
        .distinct()
        .limit(length)
        .boxed()
        .toArray(Integer[]::new);
  }
}
