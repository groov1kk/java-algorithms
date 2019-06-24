package com.ilya.algorithms.common;

import com.ilya.algorithms.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.iterableWithSize;

public class ThreeSumAlgorithmTest extends BaseTest {

  @Test
  public void testThreeSumAlgorithmTest() {
    int[] array = new int[] {10, 0, 40, 30, 15, 10, -20, -40};

    ThreeSumAlgorithm threeSumAlgorithm = new ThreeSumAlgorithm();
    List<int[]> sums = threeSumAlgorithm.sums(array);

    Assert.assertThat(sums, iterableWithSize(3));
    Assert.assertThat(
        sums, hasItems(new int[] {-40, 0, 40}, new int[] {-20, 10, 10}, new int[] {-40, 10, 30}));
  }
}
