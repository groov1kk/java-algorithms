package com.github.groov1kk.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class KadanesAlgorithmTest {

  private final KadanesAlgorithm kadanesAlgorithm = new KadanesAlgorithm();

  private final int[] actual;
  private final int expected;

  public KadanesAlgorithmTest(int[] actual, int expected) {
    this.actual = actual;
    this.expected = expected;
  }

  @Test
  public void testAllPositive() {
    Assert.assertThat(this.kadanesAlgorithm.maxSubArray(this.actual), is(this.expected));
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {new int[] {1, 2, 3}, 6},
          {new int[] {-3, -2, -1}, -1},
          {new int[] {-3, 2, -1, 4}, 5}
        });
  }
}
