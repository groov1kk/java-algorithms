package com.github.groov1kk.common;

import static com.github.groov1kk.utils.Utils.intArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.iterableWithSize;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ThreeSumTest {

  private final ThreeSum threeSum = new ThreeSum();

  @Test
  public void testThreeSumAlgorithmTest() {
    int[] array = intArray(10, 0, 40, 30, 15, 10, -20, -40);

    List<int[]> sums = threeSum.sums(array);

    assertThat(sums, iterableWithSize(3));
    assertThat(sums, hasItems(intArray(-40, 0, 40), intArray(-20, 10, 10), intArray(-40, 10, 30)));
  }
}
