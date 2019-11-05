package com.github.groov1kk.structures.queue;

/**
 * <a href="https://en.wikipedia.org/wiki/Queue_(abstract_data_type)">Queue</a> abstract data type.
 *
 * @param <E> Type of elements
 */
public interface Queue<E> extends Iterable<E> {

  /**
   * Inserts an {@code element} into the queue.
   *
   * @param element Element to insert
   */
  void enqueue(E element);

  /**
   * Inserts all {@code elements} into the queue.
   *
   * @param elements Elements to insert
   */
  void enqueueAll(E... elements);

  /**
   * Returns and removes the first element from the queue.
   *
   * @return First element of the queue
   */
  E dequeue();

  /**
   * Returns queue size.
   *
   * @return Queue size
   */
  int size();

  /**
   * Verifies, whether the queue is empty.
   *
   * @return Is queue empty
   */
  boolean isEmpty();
}
