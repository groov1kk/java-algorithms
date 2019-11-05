package com.github.groov1kk.structures.stack;

/**
 * Stack representation.
 *
 * @param <E> The type of elements
 */
public interface Stack<E> extends Iterable<E> {

  /**
   * Puts an element into stack.
   *
   * @param element Element to put into stack
   */
  void push(E element);

  /**
   * Returns an element from stack and removes it from stack.
   *
   * @return Element from stack
   */
  E pop();

  /**
   * Returns an element from stack, but does not removes this element from stack.
   *
   * @return Element from stack
   */
  E peek();

  /**
   * Returns current size of stack.
   *
   * @return Stack's size
   */
  int size();

  /**
   * Puts all elements into stack.
   *
   * @param elements Elements to put into stack
   */
  void pushAll(E... elements);

  /**
   * Verifies whether this stack is empty.
   *
   * @return Is stack empty
   */
  boolean isEmpty();
}
