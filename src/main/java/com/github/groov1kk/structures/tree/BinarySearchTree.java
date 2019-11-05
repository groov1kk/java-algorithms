package com.github.groov1kk.structures.tree;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;

import java.util.function.BiConsumer;

/**
 * Binary search tree implementation.
 *
 * <p>Read, write and remove operations have O(log(n)) in average and O(n) in worst case time
 * complexity.
 *
 * @param <K> Keys type
 * @param <V> Values type
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements Tree<K, V> {

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
      return new Node<>(key, value);
    }

    if (node.key.compareTo(key) == 0) {
      node.value = value;
    } else if (node.key.compareTo(key) > 0) {
      node.left = insert(node.left, key, value);
    } else {
      node.right = insert(node.right, key, value);
    }
    return node;
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
      node.right = deleteMin(temp.right);
      node.left = temp.left;
    }
    return node;
  }

  private Node<K, V> deleteMin(Node<K, V> node) {
    if (node.left == null) {
      return node.right;
    }

    node.left = deleteMin(node.left);
    return node;
  }

  private Node<K, V> min(Node<K, V> node) {
    Node<K, V> temp = node;
    while (temp.left != null) {
      temp = temp.left;
    }
    return temp;
  }

  private void traverse(BiConsumer<K, V> visitor) {
    traverse(this.root, visitor);
  }

  private void traverse(Node<K, V> node, BiConsumer<K, V> visitor) {
    if (node == null) {
      return;
    }

    traverse(node.left, visitor);
    visitor.accept(node.key, node.value);
    traverse(node.right, visitor);
  }

  @Override
  public Iterable<K> keys() {
    Queue<K> queue = new ArrayQueue<>();
    traverse((key, value) -> queue.enqueue(key));
    return queue;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> queue = new ArrayQueue<>();
    traverse((key, value) -> queue.enqueue(value));
    return queue;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    traverse((key, value) -> builder.append(String.format("[%s=%s], ", key, value)));
    return builder.replace(builder.length() - 2, builder.length(), "]").toString();
  }

  private static class Node<K, V> {

    private K key;
    private V value;

    private Node<K, V> left;
    private Node<K, V> right;

    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return String.format("[%s=%s]", this.key, this.value);
    }
  }
}
