package com.ilya.algorithms.structures.symboltables;

public interface SymbolTable<K, V> {

  V get(K key);

  void put(K key, V value);

  void delete(K key);

  boolean isEmpty();

  int size();

  Iterable<K> keys();

  Iterable<V> values();
}
