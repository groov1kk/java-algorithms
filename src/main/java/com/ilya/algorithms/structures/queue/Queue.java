package com.ilya.algorithms.structures.queue;

public interface Queue<E> extends Iterable<E> {

  void enqueue(E element);

  void enqueueAll(E... elements);

  E dequeue();

  int size();

  boolean isEmpty();
}
