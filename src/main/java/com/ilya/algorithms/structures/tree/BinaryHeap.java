package com.ilya.algorithms.structures.tree;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.ilya.algorithms.Utils.swap;

public abstract class BinaryHeap<E extends Comparable<E>> implements Iterable<E> {

  private static final int DEFAULT_CAPACITY = 10;

  protected E[] array;
  protected int cursor;

  public BinaryHeap() {
    this(DEFAULT_CAPACITY);
  }

  public BinaryHeap(int capacity) {
    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Comparable[capacity];
    this.array = localArray;
  }

  public BinaryHeap(E[] array) {
    this.array = array;
    this.cursor = this.array.length;
    buildHeap(this.cursor - 1);
  }

  public void add(E element) {
    Objects.requireNonNull(element, "Element must not be null");

    if (this.cursor == this.array.length) {
      resize(this.cursor * 2);
    }

    this.array[this.cursor] = element;
    increaseKey(this.cursor++);
  }

  private void buildHeap(int size) {
    for (int i = size / 2; i >= 0; i--) {
      heapify(i, size);
    }
  }

  protected final void resize(@Nonnegative int newSize) {
    this.array = Arrays.copyOf(this.array, newSize);
  }

  public final int size() {
    return this.cursor;
  }

  @Nullable
  public E sample() {
    if (this.cursor == 0) {
      return null;
    }

    Random random = ThreadLocalRandom.current();
    return this.array[random.nextInt(this.cursor)];
  }

  @Nullable
  public E delRandom() {
    if (this.cursor == 0) {
      return null;
    }

    Random random = ThreadLocalRandom.current();
    int index = random.nextInt(this.cursor);

    E result = this.array[index];
    swap(this.array, index, --this.cursor);
    this.array[this.cursor] = null;
    heapify(index, this.cursor - 1);

    if (this.cursor <= this.array.length / 4) {
      resize(this.array.length / 2);
    }
    return result;
  }

  protected abstract void heapify(int index, int size);

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
