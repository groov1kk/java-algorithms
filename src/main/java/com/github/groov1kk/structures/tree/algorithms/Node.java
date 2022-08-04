package com.github.groov1kk.structures.tree.algorithms;

import javax.annotation.Nullable;

/**
 * Binary tree's node representation.
 *
 * @param <K> Node's key type
 * @param <V> Node's value type
 */
public interface Node<K, V> {

  /**
   * Returns a key of the given node.
   *
   * @return key of the node
   */
  K key();

  /**
   * Returns a value of the given node.
   *
   * @return value of the node
   */
  V value();

  /**
   * Returns left subtree of the given node.
   *
   * @return left subtree
   */
  @Nullable
  Node<K, V> left();

  /**
   * Returns right subtree of the given node.
   *
   * @return right subtree
   */
  @Nullable
  Node<K, V> right();
}
