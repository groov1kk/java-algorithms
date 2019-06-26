package com.ilya.algorithms.structures.stack;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Iterator;
import java.util.Objects;

@NotThreadSafe
public class LinkedStack<E> implements Stack<E> {

  private Node<E> node;
  private int size;

  public LinkedStack() {}

  public LinkedStack(Stack<E> stack) {
    Objects.requireNonNull(stack, "Stack must not be null");

    this.size = stack.size();

    if (!stack.isEmpty()) {
      Iterator<E> iterator = stack.iterator();
      this.node = new Node<>(iterator.next());

      Node<E> temp = this.node;
      while (iterator.hasNext()) {
        temp.next = new Node<>(iterator.next());
        temp = temp.next;
      }
    }
  }

  @Override
  public void push(E element) {
    // Pasts an element to the beginning of the list
    Node<E> oldFirst = this.node;
    this.node = new Node<>(element);
    this.node.next = oldFirst;
    this.size++;
  }

  @Override
  public E pop() {
    if (isEmpty()) {
      return null;
    }

    E element = this.node.value;
    this.node = this.node.next;
    this.size--;
    return element;
  }

  @Override
  public E peek() {
    return isEmpty() ? null : this.node.value;
  }

  @Override
  public int size() {
    return this.size;
  }

  @SafeVarargs
  @Override
  public final void pushAll(E... elements) {
    for (E element : elements) {
      push(element);
    }
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedStackIterator();
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

  private static class Node<E> {

    private E value;
    private Node<E> next;

    Node(E value) {
      this.value = value;
    }
  }

  private class LinkedStackIterator implements Iterator<E> {

    private Node<E> cursor = LinkedStack.this.node;

    @Override
    public boolean hasNext() {
      return this.cursor != null;
    }

    @Override
    public E next() {
      E next = this.cursor.value;
      this.cursor = this.cursor.next;
      return next;
    }
  }
}
