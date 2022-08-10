package com.github.groov1kk.structures.tree.heap;

import com.github.groov1kk.utils.Checker;

import javax.annotation.Nonnegative;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * <a href="https://en.wikipedia.org/wiki/Binary_heap">Binary heap</a> abstract type.
 *
 * <p>This abstract class can be used to implement both max-heap and min-heap.
 *
 * @param <E> Elements type
 */
public abstract class BinaryHeap<E extends Comparable<E>> implements Iterable<E> {

  public static final int DEFAULT_CAPACITY = 10;

  protected E[] array;
  protected int cursor;

  /**
   * Creates an empty binary heap with the default capacity.
   *
   * @see #DEFAULT_CAPACITY
   */
  public BinaryHeap() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Creates an empty binary heap with a specific {@code capacity}.
   *
   * @param capacity Binary heap capacity
   */
  public BinaryHeap(@Nonnegative int capacity) {
    Checker.requireNonNegative(capacity, "Capacity must not be negative");

    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Comparable[capacity];
    this.array = localArray;
  }

  /**
   * Creates a binary heap from specific {@code array}.
   *
   * @param array Array which will be transformed into binary heap
   * @see #buildHeap(int)
   */
  public BinaryHeap(E[] array) {
    this.array = array;
    this.cursor = this.array.length;
    buildHeap(this.cursor - 1);
  }

  /**
   * Inserts a new {@code element} into binary heap.
   *
   * <p>To save heap structure after each insertion this method uses {@link #increaseKey(int)}
   * method.
   *
   * @param element Element to insert
   * @see #increaseKey(int)
   */
  public void add(E element) {
    Objects.requireNonNull(element, "Element must not be null");

    if (this.cursor == this.array.length) {
      resize(this.cursor * 2);
    }

    this.array[this.cursor] = element;
    increaseKey(this.cursor++);
  }

  /**
   * Builds a binary heap of specific size. Heap type depends on implementation of {@link
   * #heapify(int, int)} method.
   *
   * @param size Heap size
   * @see #heapify(int, int)
   */
  private void buildHeap(int size) {
    for (int i = size / 2; i >= 0; i--) {
      heapify(i, size);
    }
  }

  /**
   * Resize heap's capacity according to {@code newSize}.
   *
   * @param newSize Heap new size
   */
  protected final void resize(@Nonnegative int newSize) {
    Checker.requireNonNegative(newSize, "New size must not be negative");
    this.array = Arrays.copyOf(this.array, newSize);
  }

  /**
   * Returns a current size of the binary heap.
   *
   * @return heap size
   */
  public final int size() {
    return this.cursor;
  }

  /**
   * Rearranges array elements starting from the specific {@code index} to match heap order.
   * Depending on implementation, this method can rearrange elements to match min-heap order or
   * max-heap order.
   *
   * @param index Index of array to start create heap.
   * @param size Binary heap size
   */
  protected abstract void heapify(int index, int size);

  /**
   * Updates a key within max-heap or min-heap. This method is used to save the heap structure order
   * after insertion operation.
   *
   * @param index Index of key in array
   */
  protected abstract void increaseKey(int index);

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {

      private int it;

      @Override
      public boolean hasNext() {
        return this.it < BinaryHeap.this.cursor;
      }

      @Override
      public E next() {
        return BinaryHeap.this.array[this.it++];
      }
    };
  }

  @Override
  public String toString() {
    Iterator<E> iterator = this.iterator();
    StringBuilder builder = new StringBuilder("[");
    while (iterator.hasNext()) {
      builder.append(iterator.next());
      if (iterator.hasNext()) {
        builder.append(", ");
      }
    }
    return builder.append("]").toString();
  }
}
