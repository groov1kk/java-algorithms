package com.github.groov1kk.search;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.github.groov1kk.BaseTest;

abstract class BaseSearchTest extends BaseTest {

  protected static final int ARRAY_LENGTH = 30;
  protected static final int FROM_VALUE = -50;
  protected static final int TO_VALUE = 50;

  protected Integer[] array;

  @BeforeEach
  public void init() {
    this.array = boxed(distinctArray(ARRAY_LENGTH, FROM_VALUE, TO_VALUE));
  }

  private Integer[] boxed(int[] array) {
    return Arrays.stream(array).boxed().toArray(Integer[]::new);
  }
}
