package com.github.groov1kk.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class DutchFlagTest {

  private final int[] array;
  private final int[] expected;

  private final int mid;

  public DutchFlagTest(int[] array, int[] expected, int mid) {
    this.array = array;
    this.expected = expected;
    this.mid = mid;
  }

  @Test
  public void testDutchFlagProblem() {
    DutchFlag flag = DutchFlag.rearrange(this.array, this.mid);

    Assert.assertThat(flag.getArray(), is(this.expected));
    Assert.assertThat(flag.middle(), is(this.mid));
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {new int[] {1, 0, 2}, new int[] {0, 1, 2}, 1},
          {new int[] {2, 1, 1, 0, 2}, new int[] {0, 1, 1, 2, 2}, 1},
          {new int[] {0, 0, 0, 2, 1}, new int[] {0, 0, 0, 1, 2}, 1}
        });
  }
}
