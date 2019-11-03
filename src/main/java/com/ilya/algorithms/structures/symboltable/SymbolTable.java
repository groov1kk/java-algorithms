package com.ilya.algorithms.structures.symboltable;

/**
 * Associated data structure which stores it's data as key-value pair.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public interface SymbolTable<K, V> {

  V get(K key);

  void put(K key, V value);

  void remove(K key);

  boolean isEmpty();

  int size();

  Iterable<K> keys();

  Iterable<V> values();
}
