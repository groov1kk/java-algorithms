package com.github.groov1kk.common;

import com.github.groov1kk.utils.Checker;

public final class Factorial {

  public int factorial(int n) {
    Checker.requireNonNegative(n, "Number must be positive");

    if (n == 0) {
      return 1;
    }

    int result = 1;
    for (int i = 1; i <= n; i++) {
      result = result * i;
    }
    return result;
  }
}
