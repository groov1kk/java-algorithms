package com.github.groov1kk.common;

import com.github.groov1kk.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.iterableWithSize;

public class ThreeSumTest extends BaseTest {

  private static final ThreeSum threeSum = new ThreeSum();

  @Test
  public void testThreeSumAlgorithmTest() {
    int[] array = new int[] {10, 0, 40, 30, 15, 10, -20, -40};

    List<int[]> sums = threeSum.sums(array);

    Assert.assertThat(sums, iterableWithSize(3));
    Assert.assertThat(
        sums, hasItems(new int[] {-40, 0, 40}, new int[] {-20, 10, 10}, new int[] {-40, 10, 30}));
  }
}
