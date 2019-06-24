package com.ilya.algorithms.structures.tree;

import java.util.Iterator;

public interface Tree<K extends Comparable<K>, V> {

  V find(K key);

  void insert(K key, V value);

  void remove(K key);

  Iterable<K> keys();

  Iterable<V> values();
}
