package com.ilya.algorithms.sort;

import com.ilya.algorithms.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BucketSort implements Sort {

  private static final int DEFAULT_BUCKET_RANGE = 10;

  private final Sort bucketSort;
  private final int bucketRange;

  public BucketSort() {
    this(DEFAULT_BUCKET_RANGE, new InsertionSort());
  }

  public BucketSort(int bucketRange) {
    this(bucketRange, new InsertionSort());
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
      buckets.add(new ArrayList<>());
    }

    for (int element : array) {
      buckets.get((element - min) / this.bucketRange).add(element);
    }

    int k = 0;
    for (List<Integer> bucket : buckets) {
      int[] ints = bucket.stream().mapToInt(x -> x).toArray();
      for (int i : this.bucketSort.sort(ints)) {
        array[k++] = i + min;
      }
    }
    return array;
  }
}
