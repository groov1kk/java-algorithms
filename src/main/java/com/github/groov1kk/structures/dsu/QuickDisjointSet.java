package com.github.groov1kk.structures.dsu;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Uses path compression to find a root of an element. This structure <b>does not apply {@code null}
 * elements</b>.
 *
 * @param <E> The type of elements
 */
@NotThreadSafe
public class QuickDisjointSet<E> implements DisjointSet<E> {

  private final Map<E, E> elements = new HashMap<>();
  private final Map<E, Integer> ranks = new HashMap<>();

  @Override
  public void add(E element) {
    Objects.requireNonNull(element, "Element must not be null");

    this.elements.put(element, element);
    this.ranks.put(element, 1);
  }

  @Override
  public E find(E element) {
    return Objects.equals(this.elements.get(element), element)
        ? element
        : this.elements.compute(element, (e, p) -> find(this.elements.get(e)));
  }

  @Override
  public void union(E x, E y) {
    E xRoot = find(x);
    E yRoot = find(y);

    if (xRoot.equals(yRoot)) {
      return;
    }

    if (this.ranks.get(xRoot) < this.ranks.get(yRoot)) {
      this.elements.put(xRoot, yRoot);
    } else {
      this.elements.put(yRoot, xRoot);
      if (this.ranks.get(xRoot).equals(this.ranks.get(yRoot))) {
        this.ranks.compute(xRoot, (element, rank) -> ++rank);
      }
    }
  }

  @Override
  public boolean connected(E x, E y) {
    return Objects.equals(find(x), find(y));
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
