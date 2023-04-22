package com.github.groov1kk.common;

import static com.github.groov1kk.utils.Utils.intArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KadanesTest {

  @ParameterizedTest
  @MethodSource("data")
  public void testAllPositive(int[] actual, int expected) {
    assertThat(Kadanes.maxSubArray(actual), is(expected));
  }

  public static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(intArray(1, 2, 3), 6),
        Arguments.of(intArray(-3, -2, -1), -1),
        Arguments.of(intArray(-3, 2, -1, 4), 5));
  }
}
