package com.ilya.algorithms.structures.dsu;

/**
 * <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint set</a>
 * representation.
 *
 * @param <E> The type of elements
 */
public interface DisjointSet<E> extends Iterable<E> {

  /**
   * Adds an element to disjoint set.
   *
   * @param element Element to add
   */
  void add(E element);

  /**
   * Finds a root of the given element inside of disjoint set.
   *
   * @param element Element to find it's root
   * @return Element's root
   */
  E find(E element);

  /**
   * Unions two elements.
   *
   * @param x First element to join
   * @param y Second element to join
   */
  void union(E x, E y);

  /**
   * Verifies whether two elements are connected (have the same union).
   *
   * @param x First element to check
   * @param y Second element to check
   * @return Are elements connected
   */
  boolean connected(E x, E y);

  /**
   * Returns a current size of disjoint set.
   *
   * @return Size of the set
   */
  int size();
}
