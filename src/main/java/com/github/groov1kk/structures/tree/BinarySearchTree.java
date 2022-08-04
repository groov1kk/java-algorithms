package com.github.groov1kk.structures.tree;

import java.util.Objects;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;
import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.InorderTreeWalk;
import com.github.groov1kk.structures.tree.algorithms.Node;

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

  private VisitedTraversal<Node<K, V>> traversal;
  private NodeImpl<K, V> root;

  public BinarySearchTree() {
    this(new InorderTreeWalk<>());
  }

  public BinarySearchTree(VisitedTraversal<Node<K, V>> traversal) {
    this.traversal = Objects.requireNonNull(traversal);
  }

  public void setTraversal(VisitedTraversal<Node<K, V>> traversal) {
    this.traversal = Objects.requireNonNull(traversal);
  }

  @Override
  public V find(K key) {
    NodeImpl<K, V> node = this.root;
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

  private NodeImpl<K, V> insert(NodeImpl<K, V> node, K key, V value) {
    if (node == null) {
      return new NodeImpl<>(key, value);
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

  private NodeImpl<K, V> remove(NodeImpl<K, V> node, K key) {
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

      NodeImpl<K, V> temp = node;
      node = min(temp.right);
      node.right = deleteMin(temp.right);
      node.left = temp.left;
    }
    return node;
  }

  private NodeImpl<K, V> deleteMin(NodeImpl<K, V> node) {
    if (node.left == null) {
      return node.right;
    }

    node.left = deleteMin(node.left);
    return node;
  }

  private NodeImpl<K, V> min(NodeImpl<K, V> node) {
    NodeImpl<K, V> temp = node;
    while (temp.left != null) {
      temp = temp.left;
    }
    return temp;
  }

  @Override
  public Iterable<K> keys() {
    Queue<K> queue = new ArrayQueue<>();
    traversal.traverse(root, node -> queue.enqueue(node.key()));
    return queue;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> queue = new ArrayQueue<>();
    traversal.traverse(root, node -> queue.enqueue(node.value()));
    return queue;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    traversal.traverse(root, builder::append);
    return builder.replace(builder.length() - 2, builder.length(), "]").toString();
  }

  private static final class NodeImpl<K, V> implements Node<K, V> {

    private K key;
    private V value;

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

    @Nullable
    @Override
    public NodeImpl<K, V> right() {
      return right;
    }

    @Override
    public String toString() {
      return String.format("[%s=%s]", this.key, this.value);
    }
  }
}
