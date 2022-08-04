package com.github.groov1kk.structures.tree;

import java.util.Objects;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.VisitedTraversal;
import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.tree.algorithms.InorderTreeWalk;
import com.github.groov1kk.structures.tree.algorithms.Node;

public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {

  private VisitedTraversal<Node<K, V>> traversal;

  private NodeImpl<K, V> root;

  public RedBlackTree() {
    this(new InorderTreeWalk<>());
  }

  public RedBlackTree(VisitedTraversal<Node<K, V>> traversal) {
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
    this.root.isRed = false;
  }

  private NodeImpl<K, V> insert(NodeImpl<K, V> node, K key, V value) {
    if (node == null) {
      return new NodeImpl<>(key, value, true);
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

  private void flipColors(NodeImpl<K, V> node) {
    node.isRed = true;
    node.left.isRed = false;
    node.right.isRed = false;
  }

  private NodeImpl<K, V> rotateRight(NodeImpl<K, V> node) {
    NodeImpl<K, V> x = node.left;
    node.left = x.right;
    x.right = node;
    x.isRed = node.isRed;
    node.isRed = true;
    return x;
  }

  private NodeImpl<K, V> rotateLeft(NodeImpl<K, V> node) {
    NodeImpl<K, V> x = node.right;
    node.right = x.left;
    x.left = node;
    x.isRed = node.isRed;
    node.isRed = true;
    return x;
  }

  private boolean isRed(NodeImpl<K, V> node) {
    return node != null && node.isRed;
  }

  @Override
  public void remove(K key) {
    if (!isRed(this.root.left) && !isRed(this.root.right)) {
      this.root.isRed = true;
    }

    this.root = remove(this.root, key);
  }

  private NodeImpl<K, V> remove(NodeImpl<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    if (key.compareTo(node.key) < 0) {
      if (!isRed(node.left) && !isRed(node.left.left)) {
        node = moveRedLeft(node);
      }
      node.left = remove(node.left, key);
    } else {
      if (isRed(node.left)) {
        node = rotateRight(node);
      }

      if (key.compareTo(node.key) == 0 && (node.right == null)) {
        return null;
      }

      if (!isRed(node.right) && !isRed(node.right.left)) {
        node = moveRedRight(node);
      }

      if (key.compareTo(node.key) == 0) {
        NodeImpl<K, V> min = min(node.right);
        node.key = min.key;
        node.value = min.value;
        node.right = removeMin(node.right);
      } else {
        node.right = remove(node.right, key);
      }
    }

    if (isRed(node.right)) {
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

  private NodeImpl<K, V> moveRedRight(NodeImpl<K, V> node) {
    flipColors(node);
    if (isRed(node.left.left)) {
      node = rotateRight(node);
      flipColors(node);
    }
    return node;
  }

  private NodeImpl<K, V> moveRedLeft(NodeImpl<K, V> node) {
    flipColors(node);
    if (isRed(node.left.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      flipColors(node);
    }
    return node;
  }

  private NodeImpl<K, V> removeMin(NodeImpl<K, V> node) {
    if (node.left == null) {
      return node.right;
    }
    node.left = removeMin(node.left);
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
    traversal.traverse(this.root, node -> queue.enqueue(node.key()));
    return queue;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> queue = new ArrayQueue<>();
    traversal.traverse(this.root, node -> queue.enqueue(node.value()));
    return queue;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    traversal.traverse(this.root, builder::append);
    return builder.append("]").toString();
  }

  private static class NodeImpl<K, V> implements Node<K, V> {

    private K key;
    private V value;

    private NodeImpl<K, V> left;
    private NodeImpl<K, V> right;

    private boolean isRed;

    private NodeImpl(K key, V value, boolean isRed) {
      this.key = key;
      this.value = value;
      this.isRed = isRed;
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

    @Nullable
    @Override
    public Node<K, V> right() {
      return right;
    }

    @Override
    public String toString() {
      return "[" + this.key + "=" + this.value + " " + (this.isRed ? "Red" : "Black") + "]";
    }
  }
}
