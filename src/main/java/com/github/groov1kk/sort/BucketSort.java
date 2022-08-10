package com.github.groov1kk.sort;

import com.github.groov1kk.utils.Checker;
import com.github.groov1kk.utils.Utils;

import javax.annotation.Nonnegative;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Bucket sort algorithm.
 *
 * <p>This implementation uses another sorting algorithm for sub-arrays sorting in every bucket. By
 * default it uses <b>Insertion sort</b>. Another sorting algorithm also could be injected via
 * constructor.
 *
 * <p>Bucket range also could be provided via constructor. Buckets size calculates dynamically
 * according to min and max elements of array values.
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

  public BucketSort(@Nonnegative int bucketRange) {
    this(bucketRange, DEFAULT_BUCKET_SORT);
  }

  public BucketSort(@Nonnegative int bucketRange, Sort bucketSort) {
    this.bucketRange = Checker.requireNonNegative(bucketRange, "Bucket range must not be negative");
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
      if (bucket.size() > 0) {
        int[] ints = bucket.stream().mapToInt(x -> x).toArray();
        for (int i : this.bucketSort.sort(ints)) {
          array[k++] = i;
        }
      }
    }
    return array;
  }
}
