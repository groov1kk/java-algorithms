package com.ilya.algorithms.structures.symboltable;

/**
 * Associated data structure which stores it's data as key-value pair.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public interface SymbolTable<K, V> {

  /**
   * Returns a value which associated with given {@code key}.
   *
   * @param key Key which associated with a value
   * @return Value from symbol table
   */
  V get(K key);

  /**
   * Inserts key-value pair into symbol table.
   *
   * @param key Key to insert
   * @param value Value to insert
   */
  void put(K key, V value);

  /**
   * Removes key-value pair from symbol table.
   *
   * @param key Key of key-value pair to remove
   */
  void remove(K key);

  /**
   * Verifies whether this symbol table is empty or not.
   *
   * @return Is symbol table empty
   */
  boolean isEmpty();

  /**
   * Returns a size of symbol table.
   *
   * @return Symbol table size
   */
  int size();

  /**
   * Returns an iterable instance of all keys from the current symbol table.
   *
   * @return All keys
   */
  Iterable<K> keys();

  /**
   * Returns an iterable instance of all values from the current symbol table.
   *
   * @return All values
   */
  Iterable<V> values();
}
