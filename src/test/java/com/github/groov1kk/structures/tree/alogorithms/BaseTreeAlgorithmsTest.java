package com.github.groov1kk.structures.tree.alogorithms;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.tree.algorithms.Node;

public class BaseTreeAlgorithmsTest {

  protected static class NodeImpl<K, V> implements Node<K, V> {

    private final K key;
    private final V value;

    private NodeImpl<K, V> left;
    private NodeImpl<K, V> right;

    NodeImpl(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K key() {
      return key;
    }

    @Override
    public V value() {
      return value;
    }

    @Nullable
    @Override
    public Node<K, V> left() {
      return left;
    }

    public void setLeft(NodeImpl<K, V> left) {
      this.left = left;
    }

    @Nullable
    @Override
    public Node<K, V> right() {
      return right;
    }

    public void setRight(NodeImpl<K, V> right) {
      this.right = right;
    }
  }
}
