package com.ilya.algorithms.structures.graph;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.*;

/**
 * Undirected graph implementation. Does not apply {@code null} elements. Supports cycles and
 * parallel edges.
 *
 * <p>Notice, that {@link #addEdge(Object, Object)} method creates additional vertexes, if they
 * haven't been in this graph before invocation.
 *
 * @param <V> Vertexes type
 */
@NotThreadSafe
public class UndirectedGraph<V> implements Graph<V> {

  private final Map<V, List<V>> adjacent = new HashMap<>();

  private int edges;

  @Override
  public final void addVertex(V vertex) {
    Objects.requireNonNull(vertex, "Vertex must not be null");

    this.adjacent.computeIfAbsent(vertex, key -> new LinkedList<>());
  }

  @Override
  public void addEdge(V either, V other) {
    Objects.requireNonNull(either, "Either vertex must not be null");
    Objects.requireNonNull(other, "Other vertex must not be null");

    addVertex(either);
    addVertex(other);

    this.adjacent.get(either).add(other);

    // Cycles aware
    if (!either.equals(other)) {
      this.adjacent.get(other).add(either);
    }
    this.edges++;
  }

  @Override
  public void removeVertex(V vertex) {
    if (vertex == null) {
      return;
    }

    for (V adjacentVertex : adjacent(vertex)) {
      List<V> adjacent = this.adjacent.get(adjacentVertex);
      while (adjacent.remove(vertex)) {
        this.edges--;
      }
    }

    this.adjacent.remove(vertex);
  }

  @Override
  public void removeEdge(V either, V other) {
    if (isAdjacent(either, other)) {
      this.adjacent.get(either).remove(other);
      this.adjacent.get(other).remove(either);
      this.edges--;
    }
  }

  @Override
  public Iterable<V> adjacent(V vertex) {
    return this.adjacent.get(vertex);
  }

  @Override
  public final boolean isAdjacent(V either, V other) {
    return this.adjacent.containsKey(either) && this.adjacent.get(either).contains(other);
  }

  @Override
  public int vertexes() {
    return this.adjacent.keySet().size();
  }

  @Override
  public int edges() {
    return this.edges;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    this.adjacent.forEach(
        (vertex, edges) -> {
          builder.append("[").append(vertex.toString()).append(":");
          edges.forEach(
              edge -> {
                builder
                    .append("[")
                    .append(vertex.toString())
                    .append("-")
                    .append(edge.toString())
                    .append("]")
                    .append(",");
              });
          builder.replace(builder.length() - 1, builder.length(), "]").append(",");
        });
    return builder.replace(builder.length() - 1, builder.length(), "]").toString();
  }
}
