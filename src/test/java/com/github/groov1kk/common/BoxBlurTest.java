package com.github.groov1kk.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoxBlurTest {

  private final BoxBlur blurAlgorithm = new BoxBlur();

  @ParameterizedTest
  @MethodSource("data")
  void testBoxBlur(int[][] actual, int[][] expected) {
    assertThat(blurAlgorithm.boxBlur(actual), is(expected));
  }

  public static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {1, 1, 1},
              {1, 7, 1},
              {1, 1, 1}
            },
            new int[][] {{1}}),
        Arguments.of(
            new int[][] {
              {7, 4, 0, 1},
              {5, 6, 2, 2},
              {6, 10, 7, 8},
              {1, 4, 2, 0}
            },
            new int[][] {
              {5, 4},
              {4, 4}
            }));
  }
}
