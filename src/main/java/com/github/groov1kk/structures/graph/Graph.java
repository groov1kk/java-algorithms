package com.github.groov1kk.structures.graph;

import com.github.groov1kk.structures.queue.LinkedQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

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

  /**
   * Breadth-First-Search algorithm. Uses the given {@code visitor} to manipulate with each vertexes
   * which he will find.
   *
   * @param node Node from this algorithm will start
   * @param visitor Visitor that provides to manipulate with each found node
   */
  default void breadthFirstSearch(V node, Consumer<V> visitor) {
    Map<V, Boolean> marked = new HashMap<>();
    Queue<V> queue = new LinkedQueue<>();
    queue.enqueue(node);

    while (!queue.isEmpty()) {
      V vertex = queue.dequeue();
      if (!marked.getOrDefault(vertex, false)) {
        marked.put(vertex, true);
        visitor.accept(vertex);

        for (V adj : this.adjacent(vertex)) {
          queue.enqueue(adj);
        }
      }
    }
  }

  /**
   * Depth-First-Search algorithm. Uses the given {@code visitor} to manipulate with each vertexes
   * which he will find.
   *
   * @param node Node from this algorithm will start
   * @param visitor Visitor that provides to manipulate with each found node
   */
  default void depthFirstSearch(V node, Consumer<V> visitor) {
    Map<V, Boolean> marked = new HashMap<>();
    Stack<V> stack = new LinkedStack<>();
    stack.push(node);

    while (!stack.isEmpty()) {
      V vertex = stack.pop();
      if (!marked.getOrDefault(vertex, false)) {
        visitor.accept(vertex);
        marked.put(vertex, true);

        for (V adj : this.adjacent(vertex)) {
          stack.push(adj);
        }
      }
    }
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
    Objects.requireNonNull(strategy, "Strategy must not be null");

    AtomicBoolean result = new AtomicBoolean();
    this.depthFirstSearch(
        either,
        vertex -> {
          if (strategy.test(vertex, other)) {
            result.set(true);
          }
        });
    return result.get();
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
  default Iterable<V> path(@Nullable V either, @Nullable V other) {
    return path(either, other, Objects::equals);
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
  default Iterable<V> path(V either, V other, BiPredicate<V, V> strategy) {
    Objects.requireNonNull(strategy, "Strategy must not be null");

    if (!this.hasPath(either, other, strategy)) {
      return null;
    }

    Map<V, V> edgeTo = new HashMap<>();
    this.depthFirstSearch(
        either,
        vertex -> {
          for (V adj : this.adjacent(vertex)) {
            if (!edgeTo.containsKey(adj) && !strategy.test(adj, vertex)) {
              edgeTo.put(adj, vertex);
            }
          }
        });

    Stack<V> stack = new LinkedStack<>();
    for (V x = other; !strategy.test(x, either); x = edgeTo.get(x)) {
      stack.push(x);
    }
    stack.push(either);
    return stack;
  }
}
