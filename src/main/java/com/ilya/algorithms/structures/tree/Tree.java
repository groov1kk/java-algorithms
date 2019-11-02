package com.ilya.algorithms.structures.tree;

/**
 * Binary tree data structure.
 *
 * <p>Stores it's data as key-value pairs, where keys determine the structure of the tree.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public interface Tree<K extends Comparable<K>, V> {

  /**
   * Returns a value from the tree which associated with the given key.
   *
   * @param key Associated key
   * @return Value
   */
  V find(K key);

  /**
   * Inserts key-value pair into tree.
   *
   * @param key Key to insert
   * @param value Value to insert.
   */
  void insert(K key, V value);

  /**
   * Removes a key-value pair from the tree.
   *
   * @param key Key to remove
   */
  void remove(K key);

  /**
   * List of all keys from the each node of the tree.
   *
   * @return Tree's keys
   */
  Iterable<K> keys();

  /**
   * List of all values from the each node of the tree.
   *
   * @return Tree's values.
   */
  Iterable<V> values();
}
