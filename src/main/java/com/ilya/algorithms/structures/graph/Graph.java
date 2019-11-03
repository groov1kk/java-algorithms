package com.ilya.algorithms.structures.graph;

/**
 * Graph abstract type.
 *
 * @param <V> Vertexes type
 */
public interface Graph<V> {

  /**
   * Adds a vertex into this graph.
   *
   * @param vertex Vertex to add
   */
  void addVertex(V vertex);

  /**
   * Adds and edge between two vertexes into this graph.
   *
   * @param either First vertex
   * @param other Second vertex
   */
  void addEdge(V either, V other);

  /**
   * Removes the given {@code vertex} and all it's edges.
   *
   * @param vertex Vertex to remove
   */
  void removeVertex(V vertex);

  /**
   * Removes edge between two vertexes.
   *
   * @param either First vertex
   * @param other Second vertex
   */
  void removeEdge(V either, V other);

  /**
   * Returns adjacent vertexes of the given {@code vertex}.
   *
   * @param vertex Vertex which adjacent nodes have to be returned.
   * @return Adjacent vertexes
   */
  Iterable<V> adjacent(V vertex);

  /**
   * Verifies, whether {@code either} vertex has connection with {@code other} vertex.
   *
   * @param either First vertex
   * @param other Second vertex
   * @return Are vertexes connected
   */
  boolean isAdjacent(V either, V other);

  /**
   * Returns amount of vertexes in this graph.
   *
   * @return amount of vertexes
   */
  int vertexes();

  /**
   * Returns amount of edges in this graph.
   *
   * @return amount of edges
   */
  int edges();
}
