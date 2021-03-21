package com.github.groov1kk.structures.graph;

import com.github.groov1kk.structures.queue.LinkedQueue;
import com.github.groov1kk.structures.queue.Queue;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

import java.util.HashMap;
import java.util.Map;
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
   * @param node node from this algorithm will start
   * @param visitor visitor that provides to manipulate with each found node
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
   * which he will * find.
   *
   * @param node node from this algorithm will start
   * @param visitor visitor that provides to manipulate with each found node
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
}
