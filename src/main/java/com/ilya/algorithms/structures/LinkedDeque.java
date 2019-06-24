package com.ilya.algorithms.structures;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Iterator;
import java.util.Objects;

@NotThreadSafe
public class LinkedDeque<E> implements Deque<E> {

  private Node<E> left;
  private Node<E> right;
  private int size;

  public LinkedDeque() {}

  public LinkedDeque(Deque<E> deque) {
    Objects.requireNonNull(deque, "Deque must not be null").forEach(this::pushRight);
  }

  @Override
  public void pushLeft(E element) {
    Node<E> node = new Node<>(element);
    if (this.left == null) {
      this.left = node;
      this.right = this.left;
    } else {
      Node<E> oldLeft = this.left;
      this.left = node;
      this.left.next = oldLeft;
      oldLeft.prev = this.left;
    }
    this.size++;
  }

  @Override
  public void pushRight(E element) {
    Node<E> node = new Node<>(element);
    if (this.right == null) {
      this.right = node;
      this.left = this.right;
    } else {
      Node<E> oldRight = this.right;
      this.right = node;
      oldRight.next = this.right;
      this.right.prev = oldRight;
    }
    this.size++;
  }

  @Override
  public E popLeft() {
    E element = this.left.value;
    this.left = this.left.next;

    if (this.left != null) {
      this.left.prev = null;
    }

    this.size--;
    if (isEmpty()) {
      this.left = null;
      this.right = null;
    }
    return element;
  }

  @Override
  public E popRight() {
    E element = this.right.value;
    this.right = this.right.prev;

    if (this.right != null) {
      this.right.next = null;
    }

    this.size--;
    if (isEmpty()) {
      this.left = null;
      this.right = null;
    }
    return element;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedDequeIterator();
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

  private class LinkedDequeIterator implements Iterator<E> {

    private Node<E> node = LinkedDeque.this.left;

    @Override
    public boolean hasNext() {
      return this.node != null;
    }

    @Override
    public E next() {
      E next = this.node.value;
      this.node = this.node.next;
      return next;
    }
  }

  private static class Node<E> {

    private E value;
    private Node<E> prev;
    private Node<E> next;

    Node(E value) {
      this.value = value;
    }
  }
}
