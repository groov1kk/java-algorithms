package com.github.groov1kk.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KadanesTest {

  private static final Kadanes kadanesAlgorithm = new Kadanes();

  @ParameterizedTest
  @MethodSource("data")
  public void testAllPositive(int[] actual, int expected) {
    assertThat(kadanesAlgorithm.maxSubArray(actual), is(expected));
  }

  public static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3}, 6),
        Arguments.of(new int[] {-3, -2, -1}, -1),
        Arguments.of(new int[] {-3, 2, -1, 4}, 5));
  }
}
