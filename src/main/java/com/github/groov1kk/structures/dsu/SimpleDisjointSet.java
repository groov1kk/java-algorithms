package com.github.groov1kk.structures.dsu;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@NotThreadSafe
public class SimpleDisjointSet<E> implements DisjointSet<E> {

  private final Map<E, E> elements = new HashMap<>();

  @Override
  public void add(E element) {
    Objects.requireNonNull(element, "Element must not be null");

    this.elements.put(element, element);
  }

  @Override
  public E find(E element) {
    return this.elements.get(element);
  }

  @Override
  public void union(E x, E y) {
    E xRoot = this.elements.get(x);
    E yRoot = this.elements.get(y);

    for (E element : this.elements.keySet()) {
      if (this.elements.get(element).equals(xRoot)) {
        this.elements.put(element, yRoot);
      }
    }
  }

  @Override
  public boolean connected(E x, E y) {
    return Objects.equals(this.elements.get(x), this.elements.get(y));
  }

  @Override
  public int size() {
    return this.elements.size();
  }

  @Override
  public String toString() {
    return this.elements.toString();
  }

  @Override
  public Iterator<E> iterator() {
    return this.elements.keySet().iterator();
  }
}
