package com.github.groov1kk.structures.stack;

import com.github.groov1kk.utils.Checker;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Simple stack implementation. Based on arrays with dynamic size.
 *
 * @param <E> Type of elements.
 */
@NotThreadSafe
public class ArrayStack<E> implements Stack<E> {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  private E[] array;
  private int cursor;

  /** Creates an instance of array stack with the default capacity (10). */
  public ArrayStack() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Creates an instance of array stack with a specific capacity.
   *
   * @param initialCapacity Initial capacity of stack array
   */
  public ArrayStack(@Nonnegative int initialCapacity) {
    Checker.requireNonNegative(initialCapacity, "Initial capacity must not be negative");

    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Object[initialCapacity];
    this.array = localArray;
  }

  /**
   * Transforms an instance of a stack to array stack.
   *
   * @param stack stack instance to transform
   */
  public ArrayStack(Stack<E> stack) {
    Objects.requireNonNull(stack, "Stack must not be null");

    int size = stack.size();
    this.cursor = size;

    int newCapacity = size * 2;
    if (newCapacity < 1 || newCapacity > MAX_ARRAY_SIZE) {
      newCapacity = MAX_ARRAY_SIZE;
    }

    @SuppressWarnings("unchecked")
    E[] localArray = (E[]) new Object[newCapacity];
    this.array = localArray;

    for (E element : stack) {
      this.array[--size] = element;
    }
  }

  @SafeVarargs
  public static <E> ArrayStack<E> of(E... elements) {
    ArrayStack<E> stack = new ArrayStack<>();
    stack.pushAll(elements);
    return stack;
  }

  @Override
  public void push(E element) {
    if (this.cursor == this.array.length) {
      resize(this.array.length * 2);
    }
    this.array[this.cursor++] = element;
  }

  @Override
  @Nullable
  public E pop() {
    if (isEmpty()) {
      return null;
    }

    E element = this.array[--this.cursor];
    this.array[this.cursor] = null;

    if (this.cursor < this.array.length / 4) {
      resize(this.array.length / 2);
    }
    return element;
  }

  @Override
  public E peek() {
    return this.array[this.cursor - 1];
  }

  @Override
  public int size() {
    return this.cursor;
  }

  @SafeVarargs
  @Override
  public final void pushAll(E... elements) {
    int expectedCapacity = this.cursor + elements.length;

    if (expectedCapacity < 1 || expectedCapacity > MAX_ARRAY_SIZE) {
      throw new IllegalArgumentException("Amount of elements is too large");
    }

    if (expectedCapacity == this.array.length) {
      resize(expectedCapacity * 2);
    }

    for (E element : elements) {
      push(element);
    }
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  private void resize(int newSize) {
    if (newSize < 1 || newSize > MAX_ARRAY_SIZE) {
      newSize = MAX_ARRAY_SIZE;
    }

    this.array = Arrays.copyOf(this.array, newSize);
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayStackIterator();
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

  private class ArrayStackIterator implements Iterator<E> {

    private int it = cursor - 1;

    @Override
    public boolean hasNext() {
      return this.it >= 0;
    }

    @Override
    public E next() {
      return array[this.it--];
    }
  }
}
