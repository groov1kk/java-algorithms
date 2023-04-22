package com.github.groov1kk.common;

import static com.github.groov1kk.utils.Utils.intArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.api.Test;

public class HeapsTest {

  @Test
  public void testHeapAlgorithm() {
    int[] array = {1, 2, 3};

    assertThat(
        Heaps.generate(array),
        containsInAnyOrder(
            intArray(1, 2, 3),
            intArray(2, 1, 3),
            intArray(3, 1, 2),
            intArray(3, 2, 1),
            intArray(1, 3, 2),
            intArray(2, 3, 1)));
  }
}
