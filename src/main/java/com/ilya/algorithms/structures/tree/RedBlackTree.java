package com.ilya.algorithms.structures.tree;

import com.ilya.algorithms.structures.queue.ArrayQueue;
import com.ilya.algorithms.structures.queue.Queue;

import java.util.function.Consumer;

public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {

  private Node<K, V> root;

  @Override
  public V find(K key) {
    Node<K, V> node = this.root;
    while (node != null) {
      if (node.key.compareTo(key) == 0) {
        return node.value;
      }
      node = node.key.compareTo(key) > 0 ? node.left : node.right;
    }
    return null;
  }

  @Override
  public void insert(K key, V value) {
    this.root = insert(this.root, key, value);
  }

  private Node<K, V> insert(Node<K, V> node, K key, V value) {
    if (node == null) {
      return new Node<>(key, value, true);
    }

    if (node.key.compareTo(key) == 0) {
      node.value = value;
    } else if (node.key.compareTo(key) > 0) {
      node.left = insert(node.left, key, value);
    } else {
      node.right = insert(node.right, key, value);
    }

    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }

    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }

    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }
    return node;
  }

  private void flipColors(Node<K, V> node) {
    node.isRed = true;
    node.left.isRed = false;
    node.right.isRed = false;
  }

  private Node<K, V> rotateRight(Node<K, V> node) {
    Node<K, V> x = node.left;
    node.left = x.right;
    x.right = node;
    x.isRed = node.isRed;
    node.isRed = true;
    return x;
  }

  private Node<K, V> rotateLeft(Node<K, V> node) {
    Node<K, V> x = node.right;
    node.right = x.left;
    x.left = node;
    x.isRed = node.isRed;
    node.isRed = true;
    return x;
  }

  private boolean isRed(Node<K, V> node) {
    return node != null && node.isRed;
  }

  @Override
  public void remove(K key) {
    this.root = remove(this.root, key);
  }

  private Node<K, V> remove(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    if (node.key.compareTo(key) > 0) {
      node.left = remove(node.left, key);
    } else if (node.key.compareTo(key) < 0) {
      node.right = remove(node.right, key);
    } else {
      if (node.right == null) {
        return node.left;
      } else if (node.left == null) {
        return node.right;
      }

      Node<K, V> temp = node;
      node = min(temp.right);
      node.right = removeMin(temp.right);
      node.left = temp.left;
    }
    return node;
  }

  private Node<K, V> removeMin(Node<K, V> node) {
    if (node.left == null) {
      return node.right;
    }
    node.left = removeMin(node.left);
    return node;
  }

  private Node<K, V> min(Node<K, V> node) {
    Node<K, V> temp = node;
    while (temp.left != null) {
      temp = temp.left;
    }
    return temp;
  }

  private void traverse(Node<K, V> node, Consumer<Node<K, V>> visitor) {
    if (node == null) {
      return;
    }

    traverse(node.left, visitor);
    visitor.accept(node);
    traverse(node.right, visitor);
  }

  @Override
  public Iterable<K> keys() {
    Queue<K> queue = new ArrayQueue<>();
    traverse(this.root, x -> queue.enqueue(x.key));
    return queue;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> queue = new ArrayQueue<>();
    traverse(this.root, x -> queue.enqueue(x.value));
    return queue;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    traverse(this.root, builder::append);
    return builder.append("]").toString();
  }

  private static class Node<K, V> {

    private K key;
    private V value;

    private Node<K, V> left;
    private Node<K, V> right;
    private boolean isRed;

    private Node(K key, V value, boolean isRed) {
      this.key = key;
      this.value = value;
      this.isRed = isRed;
    }

    @Override
    public String toString() {
      return "[" + this.key + "=" + this.value + " " + (this.isRed ? "Red" : "Black") + "]";
    }
  }
}
