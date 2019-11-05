package com.github.groov1kk.structures.queue;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Iterator;
import java.util.Objects;

@NotThreadSafe
public class LinkedQueue<E> implements Queue<E> {

  private Node<E> first;
  private Node<E> last;
  private int size;

  public LinkedQueue() {}

  public LinkedQueue(Queue<E> queue) {
    Objects.requireNonNull(queue, "Queue must not be null").forEach(this::enqueue);
  }

  @Override
  public void enqueue(E element) {
    Node<E> node = new Node<>(element);
    if (this.first == null) {
      this.first = node;
      this.last = this.first;
    } else {
      this.last.next = node;
      this.last = this.last.next;
    }
    this.size++;
  }

  @SafeVarargs
  @Override
  public final void enqueueAll(E... elements) {
    for (E element : elements) {
      enqueue(element);
    }
  }

  @Override
  public E dequeue() {
    E element = this.first.value;
    this.first = this.first.next;
    this.size--;
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
    return new LinkedQueueIterator();
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

  private class LinkedQueueIterator implements Iterator<E> {

    private Node<E> node = LinkedQueue.this.first;

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
    private Node<E> next;

    Node(E value) {
      this.value = value;
    }
  }
}
