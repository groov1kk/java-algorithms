package com.ilya.algorithms.structures.symboltable;

import com.ilya.algorithms.structures.queue.ArrayQueue;
import com.ilya.algorithms.structures.queue.Queue;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class BinarySymbolTable<K extends Comparable<K>, V> implements SymbolTable<K, V> {

  private static final int DEFAULT_CAPACITY = 10;

  private K[] keys;
  private V[] values;

  private int size;

  public BinarySymbolTable() {
    this(DEFAULT_CAPACITY);
  }

  @SuppressWarnings("unchecked")
  public BinarySymbolTable(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Size must be positive");
    }

    this.keys = (K[]) new Comparable[capacity];
    this.values = (V[]) new Object[capacity];
  }

  @Override
  @Nullable
  public V get(K key) {
    if (isEmpty()) {
      return null;
    }

    int rank = rank(key);

    if (rank < this.size && this.keys[rank].compareTo(key) == 0) {
      return this.values[rank];
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    Objects.requireNonNull(key, "Key must not br null");

    int rank = rank(key);

    if (rank < this.size && this.keys[rank].compareTo(key) == 0) {
      this.values[rank] = value;
      return;
    }

    if (this.size == this.keys.length) {
      resize(2 * this.size);
    }

    for (int i = this.size; i > rank; i--) {
      this.keys[i] = this.keys[i - 1];
      this.values[i] = this.values[i - 1];
    }

    this.keys[rank] = key;
    this.values[rank] = value;

    this.size++;
  }

  @Override
  public void remove(K key) {
    if (isEmpty()) {
      return;
    }

    int rank = rank(key);

    if (rank < this.size && this.keys[rank].compareTo(key) == 0) {
      for (int i = this.size - 1; i > rank; i--) {
        this.keys[i - 1] = this.keys[i];
        this.values[i - 1] = this.values[i];
      }

      this.size--;

      this.keys[this.size] = null;
      this.values[this.size] = null;
    }
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterable<K> keys() {
    Queue<K> queue = new ArrayQueue<>(this.size);
    queue.enqueueAll(this.keys);
    return queue;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> queue = new ArrayQueue<>(this.size);
    queue.enqueueAll(this.values);
    return queue;
  }

  private void resize(int newSize) {
    if (size < 0) {
      throw new IllegalArgumentException("Size must be positive");
    }

    this.keys = Arrays.copyOf(this.keys, newSize);
    this.values = Arrays.copyOf(this.values, newSize);
  }

  private int rank(K key) {
    int lo = 0;
    int hi = this.size - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;

      if (this.keys[mid].compareTo(key) == 0) {
        return mid;
      } else if (this.keys[mid].compareTo(key) > 0) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  @Override
  public String toString() {
    Iterator<K> keys = keys().iterator();
    Iterator<V> values = values().iterator();

    StringBuilder builder = new StringBuilder("[");
    for (int i = 0; i < this.size; i++) {
      builder.append("[").append(keys.next()).append("=").append(values.next()).append("]");
      if (i + 1 != this.size) {
        builder.append(",").append(" ");
      }
    }
    return builder.append("]").toString();
  }
}
