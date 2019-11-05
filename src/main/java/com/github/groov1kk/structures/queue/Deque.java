package com.github.groov1kk.structures.queue;

/**
 * <a href="https://en.wikipedia.org/wiki/Double-ended_queue">Deque</a> abstraction.
 *
 * @param <E> Type of elements
 */
public interface Deque<E> extends Iterable<E> {

  /**
   * Push element from the left side (the beginning) of the Deque.
   *
   * @param element Element to push
   */
  void pushLeft(E element);

  /**
   * Push element from the right side (the end) of the Deque.
   *
   * @param element Element to push
   */
  void pushRight(E element);

  /**
   * Returns element from the left side (the beginning) and removes it from the Deque.
   *
   * @return Element from the left side of the Deque
   */
  E popLeft();

  /**
   * Returns element from the right side (the end) and removes it from the Deque.
   *
   * @return Element from the right side of the Deque
   */
  E popRight();

  /**
   * Current size of the Deque.
   *
   * @return Size of the deque
   */
  int size();

  /**
   * Checks, whether the deque is empty.
   *
   * @return Whether the deque is empty or not
   */
  boolean isEmpty();
}
