package com.ilya.algorithms.common;

public final class Fibonacci {

  public int fibonacci(int n) {
    if (n <= 0) {
      return n;
    }

    int result = 0;
    int current = 1;
    int previous = 0;

    for (int i = 1; i < n; i++) {
      result = current + previous;
      previous = current;
      current = result;
    }
    return result;
  }
}
