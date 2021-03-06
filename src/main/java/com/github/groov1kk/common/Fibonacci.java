package com.github.groov1kk.common;

import com.github.groov1kk.Checker;

/**
 * Iterative implementation of fibonacci algorithm.
 *
 * <p>Time complexity is O(n).
 */
public final class Fibonacci {

  public int fibonacci(int n) {
    Checker.requireNonNegative(n, "Number must be positive");

    if (n <= 1) {
      return n;
    }

    int result = 0;
    int current = 1;
    int previous = 0;

    for (int i = 2; i <= n; i++) {
      result = current + previous;
      previous = current;
      current = result;
    }
    return result;
  }
}
