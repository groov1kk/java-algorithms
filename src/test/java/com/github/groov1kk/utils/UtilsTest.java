package com.github.groov1kk.utils;

import static com.github.groov1kk.utils.Utils.max;
import static com.github.groov1kk.utils.Utils.min;
import static com.github.groov1kk.utils.Utils.shuffle;
import static com.github.groov1kk.utils.Utils.swap;
import static com.github.groov1kk.utils.matchers.IsIntArrayContaining.intArrayContaining;
import static com.github.groov1kk.utils.matchers.IsIntArrayContainingInAnyOrder.intArrayContainingInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.github.groov1kk.extensions.RandomArray;
import com.github.groov1kk.extensions.RandomArrayExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomArrayExtension.class)
public class UtilsTest {

  @Test
  public void testMax() {
    int[] array = {1, 2, 3, 4, 5};

    assertThat(max(array), is(5));
  }

  @Test
  public void testMin() {
    int[] array = {1, 2, 3};

    assertThat(min(array), is(1));
  }

  @Test
  public void testSwapInts() {
    int[] array = {1, 2, 3, 4, 5};

    swap(array, 0, 1);

    assertThat(array, intArrayContaining(2, 1, 3, 4, 5));
  }

  @Test
  public void testSwapComparable() {
    Comparable<Integer>[] array = new Integer[] {1, 2, 3};

    swap(array, 0, 1);

    assertThat(array, arrayContaining(2, 1, 3));
  }

  @Test
  public void testShuffleArray(@RandomArray(length = 10, from = 1, to = 10) int[] array) {
    assertThat(
        shuffle(array.clone()),
        allOf(not(intArrayContaining(array)), intArrayContainingInAnyOrder(array)));
  }
}
