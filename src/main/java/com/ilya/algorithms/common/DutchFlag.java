package com.ilya.algorithms.common;

import javax.annotation.Nonnegative;
import java.util.Arrays;
import java.util.Objects;

import static com.ilya.algorithms.Utils.swap;

/**
 * <a href="https://en.wikipedia.org/wiki/Dutch_national_flag_problem">Dutch national flag problem
 * </a> algorithm.
 */
public final class DutchFlag {

  private final int[] array;

  private final int low;
  private final int high;

  private final int mid;

  private DutchFlag(int[] array, int mid, int low, int high) {
    this.array = array;
    this.mid = mid;
    this.low = low;
    this.high = high;
  }

  /**
   * Rearranges elements in the given {@code array} according to Dutch national flag problem.
   *
   * <p>This method provides to specify the middle element (white color). All the rest elements in
   * this array will be compared with this element and moved either before the middle element (red
   * color) or after this element (blue color).
   *
   * @param array Array to rearrange it's elements
   * @param mid Element that will be considered as middle (white color)
   * @return {@code this}
   */
  public static DutchFlag rearrange(int[] array, int mid) {
    return rearrange(array, mid, 0, array.length - 1);
  }

  /**
   * Rearranges elements in the given {@code array} according to Dutch national flag problem.
   *
   * <p>This method provides to specify the middle element (white color). All the rest elements in
   * this array will be compared with this element and moved either before middle element (red
   * color) or after this element (blue color).
   *
   * <p>Also this method applies {@code left} and {@code right} indexes of the given array, which
   * specify sub-array, where Dutch National Flag problem will be applied.
   *
   * @param array Array to rearrange it's elements
   * @param mid Element that will be considered as middle (white color).
   * @param left Index, that represents a border between red and white elements
   * @param right Index, that represents a border between white and blue elements
   * @return {@code this}
   */
  public static DutchFlag rearrange(
      int[] array, int mid, @Nonnegative int left, @Nonnegative int right) {
    Objects.requireNonNull(array, "Array must not be null");

    int low = left;
    int high = right;

    int x = left;

    while (x <= high) {
      if (array[x] < mid) {
        swap(array, low++, x++);
      } else if (array[x] > mid) {
        swap(array, x, high--);
      } else {
        x++;
      }
    }
    return new DutchFlag(array, mid, low - 1, high + 1);
  }

  /**
   * Returns rearranged array according to Dutch national flag problem.
   *
   * @return Rearranged array
   */
  public int[] getArray() {
    return this.array;
  }

  /**
   * Index that represents a border between red and white elements. Points on the last element in
   * red area.
   *
   * <p>Pay attention, that index may be negative. That's mean that there is no red color in the
   * current flag.
   *
   * @return Last index of read area.
   */
  public int lowBorder() {
    return this.low;
  }

  /**
   * Index that represents a border between white and blue elements. Points on the first element in
   * blue area.
   *
   * <p>Pay attention, that index may be greater than length of rearranged array. That's mean that
   * there is no blue color in the current
   *
   * @return First index of blue area
   */
  public int highBorder() {
    return this.high;
  }

  /**
   * Value, that represent white color.
   *
   * @return White color value
   */
  public int middle() {
    return this.mid;
  }

  @Override
  public String toString() {
    return "["
        + "Array: "
        + Arrays.toString(this.array)
        + ", "
        + "Middle: "
        + this.mid
        + ", "
        + "Low border: "
        + this.low
        + ", "
        + "High border: "
        + this.high;
  }
}
