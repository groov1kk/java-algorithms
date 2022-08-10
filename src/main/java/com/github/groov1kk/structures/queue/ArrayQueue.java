package com.github.groov1kk.structures.queue;

import com.github.groov1kk.utils.Checker;

import java.util.Iterator;
import java.util.Objects;
import javax.annotation.Nonnegative;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ArrayQueue<E> implements Queue<E> {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  private E[] array;

  private int head;
  private int tail;

  public ArrayQueue() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayQueue(@Nonnegative int capacity) {
    Checker.requireNonNegative(capacity, "Capacity must not be negative");

    @SuppressWarnings("unchecked")
    E[] array = (E[]) new Object[capacity];
    this.array = array;
  }

  public ArrayQueue(Queue<E> queue) {
    Objects.requireNonNull(queue, "Queue must not be null");

    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Object[queue.size()];
    this.array = localArray;

    for (E element : queue) {
      enqueue(element);
    }
  }

  @SafeVarargs
  public static <E> ArrayQueue<E> of(E... elements) {
    ArrayQueue<E> queue = new ArrayQueue<>();
    queue.enqueueAll(elements);
    return queue;
  }

  @Override
  public void enqueue(E element) {
    if (this.tail == this.array.length) {
      resize(this.array.length * 2);
    }

    this.array[this.tail++] = element;
  }

  @Override
  public void enqueueAll(E[] elements) {
    int expectedCapacity = size() + elements.length;

    if (expectedCapacity > MAX_ARRAY_SIZE) {
      throw new IllegalArgumentException("Amount of elements is too large");
    }

    if (expectedCapacity == this.array.length) {
      resize(expectedCapacity * 2);
    }

    for (E element : elements) {
      enqueue(element);
    }
  }

  @Override
  public E dequeue() {
    E element = this.array[this.head];
    this.array[this.head] = null;
    this.head++;
    return element;
  }

  @Override
  public final int size() {
    return this.tail - this.head;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  private void resize(int newSize) {
    if (newSize < 1 || newSize > MAX_ARRAY_SIZE) {
      newSize = MAX_ARRAY_SIZE;
    }

    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Object[newSize];
    System.arraycopy(this.array, this.head, localArray, 0, size());

    this.array = localArray;
    this.tail = size();
    this.head = 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayQueueIterator();
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

  private class ArrayQueueIterator implements Iterator<E> {

    private int it = ArrayQueue.this.head;

    @Override
    public boolean hasNext() {
      return this.it < ArrayQueue.this.tail;
    }

    @Override
    public E next() {
      return ArrayQueue.this.array[this.it++];
    }
  }
}
