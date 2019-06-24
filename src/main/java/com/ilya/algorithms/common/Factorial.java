package com.ilya.algorithms.common;

public final class Factorial {

  public int factorial(int n) {
    if (n <= 0) {
      return n;
    }

    int result = 1;
    for (int i = 2; i <= n; i++) {
      result = result * i;
    }
    return result;
  }
}
