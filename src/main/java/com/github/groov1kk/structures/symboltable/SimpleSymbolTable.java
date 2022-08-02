package com.github.groov1kk.structures.symboltable;

import com.github.groov1kk.structures.queue.ArrayQueue;
import com.github.groov1kk.structures.queue.Queue;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Iterator;
import java.util.Objects;

/**
 * Simple implementation of symbol table. Uses linked list to store its data.
 *
 * <p>Read, write and remove operations have O(n) average time complexity.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
@NotThreadSafe
public class SimpleSymbolTable<K, V> implements SymbolTable<K, V> {

  private Node<K, V> first;
  private int size;

  @Override
  @Nullable
  public V get(K key) {
    for (Node<K, V> node = this.first; node != null; node = node.next) {
      if (node.key.equals(key)) {
        return node.value;
      }
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    Objects.requireNonNull(key, "Key must not be null");

    this.size++;
    for (Node<K, V> node = this.first; node != null; node = node.next) {
      if (node.key.equals(key)) {
        node.value = value;
        return;
      }
    }

    this.first = new Node<>(key, value, this.first);
  }

  @Override
  public void remove(K key) {
    if (this.first == null) {
      return;
    }

    if (this.first.key.equals(key)) {
      this.first = this.first.next;
      this.size--;
      return;
    }

    Node<K, V> prev = this.first;
    for (Node<K, V> node = this.first.next; node != null; node = node.next) {
      if (node.key.equals(key)) {
        prev.next = node.next;
        this.size--;
        return;
      }
      prev = node;
    }
  }

  @Override
  public boolean isEmpty() {
    return this.first == null;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterable<K> keys() {
    Queue<K> result = new ArrayQueue<K>(this.size);
    for (Node<K, V> node = this.first; node != null; node = node.next) {
      result.enqueue(node.key);
    }
    return result;
  }

  @Override
  public Iterable<V> values() {
    Queue<V> result = new ArrayQueue<V>(this.size);
    for (Node<K, V> node = this.first; node != null; node = node.next) {
      result.enqueue(node.value);
    }
    return result;
  }

  private static class Node<K, V> {

    private K key;
    private V value;
    private Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  @Override
  public String toString() {
    Iterator<K> keys = keys().iterator();
    Iterator<V> values = values().iterator();

    StringBuilder builder = new StringBuilder("[");
    for (int i = 0; i < this.size; i++) {
      builder.append("[").append(keys.next()).append("=").append(values.next()).append("]");
      if (i + 1 != this.size) {
        builder.append(",").append(" ");
      }
    }
    return builder.append("]").toString();
  }
}
