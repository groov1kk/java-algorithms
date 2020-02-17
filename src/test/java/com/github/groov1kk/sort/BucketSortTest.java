package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class BucketSortTest extends BaseSortTest {

  @Test
  public void testBucketSort() {
    int[] clone = array.clone();

    Sort sort = new BucketSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }

  @Test
  public void testBucketSortCustomRange() {
    int[] clone = array.clone();

    int bucketRange = 20;
    Sort sort = new BucketSort(bucketRange);
    sort.sort(array);

    Assert.assertThat(array, isSortedAsc());
    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
  }

  @Test
  public void testBucketSortCustomRangeAndSort() {
    int[] clone = array.clone();

    int bucketRange = 20;
    Sort bucketSort = new BubbleSort();
    Sort sort = new BucketSort(bucketRange, bucketSort);
    sort.sort(array);

    Assert.assertThat(array, isSortedAsc());
    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
  }
}
