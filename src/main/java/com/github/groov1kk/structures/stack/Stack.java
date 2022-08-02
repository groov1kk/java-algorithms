package com.github.groov1kk.structures.stack;

/**
 * Stack representation.
 *
 * @param <E> The type of elements
 */
public interface Stack<E> extends Iterable<E> {

  /**
   * Puts an element into the stack.
   *
   * @param element Element to put into stack
   */
  void push(E element);

  /**
   * Returns an element from the stack and removes it from the stack.
   *
   * @return Element from stack
   */
  E pop();

  /**
   * Returns an element from the stack, but does not removes this element from the stack.
   *
   * @return Element from stack
   */
  E peek();

  /**
   * Returns the current size of the stack.
   *
   * @return Stack's size
   */
  int size();

  /**
   * Puts all elements into the stack.
   *
   * @param elements Elements to put into stack
   */
  void pushAll(E[] elements);

  /**
   * Verifies whether this stack is empty.
   *
   * @return Is stack empty
   */
  boolean isEmpty();
}
