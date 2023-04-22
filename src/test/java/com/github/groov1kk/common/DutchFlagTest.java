package com.github.groov1kk.common;

import static com.github.groov1kk.utils.Utils.intArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DutchFlagTest {

  @ParameterizedTest
  @MethodSource("data")
  public void testDutchFlagProblem(int[] array, int[] expected, int mid) {
    DutchFlag flag = DutchFlag.rearrange(array, mid);

    assertThat(flag.getArray(), is(expected));
    assertThat(flag.middle(), is(mid));
  }

  public static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(intArray(1, 0, 2), intArray(0, 1, 2), 1),
        Arguments.of(intArray(2, 1, 1, 0, 2), intArray(0, 1, 1, 2, 2), 1),
        Arguments.of(intArray(0, 0, 0, 2, 1), intArray(0, 0, 0, 1, 2), 1));
  }
}
