package com.github.groov1kk.structures.tree.alogorithms;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.tree.algorithms.Node;

public class BaseTreeAlgorithmsTest {

  //        1
  //       / \
  //     2    3
  //    / \
  //   4   5
  protected static Node<Integer, String> getTree() {
    NodeImpl<Integer, String> node = new NodeImpl<>(1, "one");
    node.setLeft(new NodeImpl<>(2, "two"));
    node.setRight(new NodeImpl<>(3, "three"));
    node.left().setLeft(new NodeImpl<>(4, "four"));
    node.left().setRight(new NodeImpl<>(5, "five"));
    return node;
  }

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
    public NodeImpl<K, V> left() {
      return left;
    }

    public void setLeft(NodeImpl<K, V> left) {
      this.left = left;
    }

    @Nullable
    @Override
    public NodeImpl<K, V> right() {
      return right;
    }

    public void setRight(NodeImpl<K, V> right) {
      this.right = right;
    }

    @Override
    public String toString() {
      return "[" + key + ": " + value + "]";
    }
  }
}
