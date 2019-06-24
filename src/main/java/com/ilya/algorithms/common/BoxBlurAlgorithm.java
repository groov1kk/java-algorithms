package com.ilya.algorithms.common;

/**
 * <a href="https://en.wikipedia.org/wiki/Box_blur">Box blur algorithm</a> implementation.
 *
 * <p>Applicable for images with delMin pixel size 3x3.
 */
public final class BoxBlurAlgorithm {

  private static final int PIXEL_SIZE = 3;

  public int[][] boxBlur(int[][] image) {
    int[][] result = new int[image.length - 2][image[0].length - 2];

    for (int i = 0; i < image.length - 2; i++) {
      for (int j = 0; j < image[i].length - 2; j++) {
        result[i][j] = calculateSum(image, j, i);
      }
    }
    return result;
  }

  /**
   * Calculates an average sum of pixels for 3x3 matrix.
   *
   * @param image 3x3 matrix
   * @param x Upper left X-axis index of matrix
   * @param y Upper left Y-axis index of matrix
   * @return Average value of all pixels
   */
  private int calculateSum(int[][] image, int x, int y) {
    int sum = 0;
    for (int i = y; i < y + PIXEL_SIZE; i++) {
      for (int j = x; j < x + PIXEL_SIZE; j++) {
        sum = sum + image[i][j];
      }
    }
    return (int) Math.floor((double) sum / 9);
  }
}
