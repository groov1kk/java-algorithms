package com.github.groov1kk.common;

import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BoxBlurTest {

  private final BoxBlur blurAlgorithm = new BoxBlur();

  private final int[][] actual;
  private final int[][] expected;

  public BoxBlurTest(int[][] actual, int[][] expected) {
    this.actual = actual;
    this.expected = expected;
  }

  @Test
  public void testBoxBlur() {
    Assert.assertThat(this.blurAlgorithm.boxBlur(this.actual), is(this.expected));
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {
            new int[][] {
              {1, 1, 1},
              {1, 7, 1},
              {1, 1, 1}
            },
            new int[][] {{1}}
          },
          {
            new int[][] {
              {7, 4, 0, 1},
              {5, 6, 2, 2},
              {6, 10, 7, 8},
              {1, 4, 2, 0}
            },
            new int[][] {
              {5, 4},
              {4, 4}
            }
          }
        });
  }
}
