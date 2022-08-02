package com.github.groov1kk.structures.graph.algorithms;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

import com.github.groov1kk.structures.Traversal;
import com.github.groov1kk.structures.graph.DirectedGraph;
import com.github.groov1kk.structures.stack.LinkedStack;
import com.github.groov1kk.structures.stack.Stack;

/**
 * Algorithm that searches for cycles in a graph. Uses DFS to find them.
 *
 * @param <V> Vertexes type
 */
public final class DirectedCycle<V> {

  private final Stack<Iterable<V>> cycles = new LinkedStack<>();

  /**
   * Creates an instance of algorithm that finds cycles in a graph.
   *
   * @param graph Graph to find cycles
   * @param strategy Graph's nodes comparison strategy
   */
  public DirectedCycle(DirectedGraph<V> graph, BiPredicate<V, V> strategy) {
    Traversal<V> cycleSearch = new DirectedCycleSearch(graph, strategy);
    for (V v : graph) {
      if (!cycleSearch.isVisited(v)) {
        cycleSearch.traverse(v);
      }
    }
  }

  /**
   * Creates an instance of algorithm that finds cycles in a graph.
   *
   * @param graph Graph to find cycles
   * @param node Graph's not to start search from
   * @param strategy Graph's nodes comparison strategy
   */
  public DirectedCycle(DirectedGraph<V> graph, V node, BiPredicate<V, V> strategy) {
    new DirectedCycleSearch(graph, strategy).traverse(node);
  }

  /**
   * Checks whether the algorithm found cycles.
   *
   * @return true if algorithm found at least one cycle
   */
  public boolean hasCycles() {
    return !cycles.isEmpty();
  }

  /**
   * Returns a list of all cycles. Every cycle consist of list of nodes that form this cycle.
   *
   * @return list of all cycles
   */
  public Iterable<Iterable<V>> getCycles() {
    return cycles;
  }

  /** DFS algorithm that used to looking for cycles in the given graph. */
  private class DirectedCycleSearch implements Traversal<V> {

    private final Set<V> visited = new HashSet<>();
    private final Set<V> onStack = new HashSet<>();
    private final Map<V, V> edgeTo = new HashMap<>();

    private final BiPredicate<V, V> strategy;
    private final DirectedGraph<V> graph;

    DirectedCycleSearch(DirectedGraph<V> graph, BiPredicate<V, V> strategy) {
      this.graph = requireNonNull(graph);
      this.strategy = requireNonNull(strategy);
    }

    @Override
    public boolean isVisited(V node) {
      return visited.contains(node);
    }

    @Override
    public void traverse(V node) {
      Stack<V> stack = new LinkedStack<>();
      stack.push(node);

      while (!stack.isEmpty()) {
        V vertex = stack.peek();
        if (isVisited(vertex)) {
          onStack.remove(vertex);
          stack.pop();
          continue;
        }

        visited.add(vertex);
        onStack.add(vertex);

        for (V adj : graph.adjacent(vertex)) {
          if (!isVisited(adj)) {
            stack.push(adj);
            edgeTo.put(adj, vertex);
          } else if (onStack.contains(adj)) {
            Stack<V> cycle = new LinkedStack<>();
            for (V x = vertex; !strategy.test(x, adj); x = edgeTo.get(x)) {
              cycle.push(x);
            }
            cycle.push(adj);
            cycles.push(cycle);
          }
        }
      }
    }
  }
}
