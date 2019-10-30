package com.ilya.algorithms.sort;

import com.ilya.algorithms.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Bucket sort algorithm. This implementation provides to set bucket range and algorithm for buckets
 * sorting. By default it uses <b>Insertion sort</b>.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n*m).
 *
 * @see #DEFAULT_BUCKET_RANGE
 * @see #DEFAULT_BUCKET_SORT
 */
public class BucketSort implements Sort {

  private static final int DEFAULT_BUCKET_RANGE = 10;
  private static final Sort DEFAULT_BUCKET_SORT = new InsertionSort();

  private final Sort bucketSort;
  private final int bucketRange;

  public BucketSort() {
    this(DEFAULT_BUCKET_RANGE, DEFAULT_BUCKET_SORT);
  }

  public BucketSort(int bucketRange) {
    this(bucketRange, DEFAULT_BUCKET_SORT);
  }

  public BucketSort(int bucketRange, Sort bucketSort) {
    this.bucketRange = bucketRange;
    this.bucketSort = Objects.requireNonNull(bucketSort, "Sort algorithm must not be null");
  }

  @Override
  public int[] sort(int[] array) {
    int min = Utils.min(array);
    int max = Utils.max(array);

    int bucketsSize = (max - min) / this.bucketRange + 1;
    List<List<Integer>> buckets = new ArrayList<>(bucketsSize);

    for (int i = 0; i < bucketsSize; i++) {
      buckets.add(new LinkedList<>());
    }

    for (int element : array) {
      buckets.get((element - min) / this.bucketRange).add(element);
    }

    int k = 0;
    for (List<Integer> bucket : buckets) {
      int[] ints = bucket.stream().mapToInt(x -> x).toArray();
      for (int i : this.bucketSort.sort(ints)) {
        array[k++] = i;
      }
    }
    return array;
  }
}
