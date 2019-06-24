package com.ilya.algorithms.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class DutchFlagProblemTest {

  private final DutchFlagProblem dutchFlag = new DutchFlagProblem();

  private final int[] array;
  private final int[] expected;

  public DutchFlagProblemTest(int[] array, int[] expected) {
    this.array = array;
    this.expected = expected;
  }

  @Test
  public void testDutchFlagProblem() {
    Assert.assertThat(this.dutchFlag.partition(this.array), is(this.expected));
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {new int[] {1, 0, 2}, new int[] {0, 1, 2}},
          {new int[] {2, 1, 1, 0, 2}, new int[] {0, 1, 1, 2, 2}},
          {new int[] {0, 0, 0, 2, 1}, new int[] {0, 0, 0, 1, 2}}
        });
  }
}
