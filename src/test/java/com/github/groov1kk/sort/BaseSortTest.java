package com.github.groov1kk.sort;

import org.junit.Before;

import com.github.groov1kk.BaseTest;

abstract class BaseSortTest extends BaseTest {

  /** Array of integer values to test. */
  protected int[] array;

  @Before
  public void init() {
    this.array = array(20, -50, 50);
  }
}
