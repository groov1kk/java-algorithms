package com.github.groov1kk.structures.graph;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.github.groov1kk.structures.graph.algorithms.BreadthFirstSearch;
import com.github.groov1kk.structures.graph.algorithms.DepthFirstPath;
import com.github.groov1kk.structures.graph.algorithms.DepthFirstSearch;

/**
 * Graph abstract type.
 *
 * @param <V> Vertexes type
 */
public interface Graph<V> extends Iterable<V> {

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
   * @return Adjacent vertices
   */
  Iterable<V> adjacent(V vertex);

  /**
   * Verifies, whether the {@code either} vertex has connection with the {@code other} vertex.
   *
   * @param either First vertex
   * @param other Second vertex
   * @return Are vertexes connected
   */
  boolean isAdjacent(V either, V other);

  /**
   * Returns an amount of vertexes in this graph.
   *
   * @return amount of vertexes
   */
  int vertices();

  /**
   * Returns an amount of edges in this graph.
   *
   * @return amount of edges
   */
  int edges();

  /**
   * Breadth-First-Search algorithm. Uses the given {@code visitor} to handle each vertexes which it
   * will find during the traverse.
   *
   * @param node Node from this algorithm will start
   * @param visitor Visitor that provides to manipulate with each found node
   */
  default BreadthFirstSearch<V> breadthFirstSearch(V node, @Nullable Consumer<V> visitor) {
    BreadthFirstSearch<V> breadthFirstSearch = new BreadthFirstSearch<>(this);
    breadthFirstSearch.traverse(node, visitor);
    return breadthFirstSearch;
  }

  /**
   * Depth-First-Search algorithm. Uses the given {@code visitor} to handle each vertexes which he
   * will find during the traverse.
   *
   * @param node Node from this algorithm will start
   * @param visitor Visitor that provides to manipulate with each found node
   */
  default DepthFirstSearch<V> depthFirstSearch(V node, @Nullable Consumer<V> visitor) {
    DepthFirstSearch<V> depthFirstSearch = new DepthFirstSearch<>(this);
    depthFirstSearch.traverse(node, visitor);
    return depthFirstSearch;
  }

  /**
   * Checks, whether the given graph contains path between two provided vertexes. Uses
   * Deep-First-Search to walk through this graph.
   *
   * <p>By default, this method compares graph's vertexes using {@link Objects#equals(Object,
   * Object)}. This strategy can apply {@code null} values: in case of one of the given nodes is
   * {@code null} but other is not - this method returns {@code false}. In case when two nodes are
   * nulls - this method returns {@code true}.
   *
   * @param either Graph's node from method will start to search desired path
   * @param other End node of desired path
   * @return Is the paths between two nodes exist
   * @see Objects#equals(Object, Object)
   */
  default boolean hasPath(@Nullable V either, @Nullable V other) {
    return hasPath(either, other, Objects::equals);
  }

  /**
   * Checks, whether the given graph contains path between two provided vertexes. Uses
   * Deep-First-Search to walk through this graph.
   *
   * <p>This method applies comparing {@code strategy}, that provides to compare graph's nodes
   * between each other.
   *
   * @param either Graph's node from method will start to search desired path
   * @param other End node of desired path
   * @param strategy Comparing strategy to compare graph's nodes
   * @return Is the paths between two nodes exist
   */
  default boolean hasPath(V either, V other, BiPredicate<V, V> strategy) {
    return new DepthFirstPath<>(this, either, strategy).hasPath(other);
  }

  /**
   * Returns a list of nodes (the path), if it exists, between two given graph's nodes. The path
   * consists of graph's nodes that are situated between {@code either} and {@code other} nodes. If
   * path does not exist - this method will return {@code null}.
   *
   * @param either Graph's node from method will start to search desired path
   * @param other End node of desired path
   * @return A list of nodes between the given ones.
   */
  @Nullable
  default Iterable<V> getPath(@Nullable V either, @Nullable V other) {
    return getPath(either, other, Objects::equals);
  }

  /**
   * Returns a list of nodes (the path), if it exists, between two given graph's nodes. The path
   * consists of graph's nodes that are situated between {@code either} and {@code other} nodes. If
   * path does not exist - this method will return {@code null}.
   *
   * <p>This method applies comparing {@code strategy}, that provides to compare graph's nodes
   * between each other.
   *
   * @param either Graph's node from method will start to search desired path
   * @param other End node of desired path
   * @param strategy Comparing strategy to compare graph's nodes
   * @return A list of nodes between the given ones.
   */
  @Nullable
  default Iterable<V> getPath(V either, V other, BiPredicate<V, V> strategy) {
    return new DepthFirstPath<>(this, either, strategy).getPath(other);
  }
}
