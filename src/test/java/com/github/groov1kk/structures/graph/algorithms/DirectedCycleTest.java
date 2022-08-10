package com.github.groov1kk.structures.graph.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.graph.DirectedGraph;

public class DirectedCycleTest {

  private static final BiPredicate<Integer, Integer> DEFAULT_STRATEGY =
      (e1, e2) -> e1.compareTo(e2) == 0;

  @Test
  @SuppressWarnings("unchecked")
  public void testHasCycle() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);
    graph.addEdge(2, 1);

    DirectedCycle<Integer> directedCycle = new DirectedCycle<>(graph, DEFAULT_STRATEGY);

    assertThat(directedCycle.hasCycles(), is(true));
    assertThat(
        directedCycle.getCycles(),
        containsInAnyOrder(containsInAnyOrder(1, 2, 3), containsInAnyOrder(1, 2)));
  }

  @Test
  public void testHasNoCycles() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);
    graph.addEdge(1, 3);

    DirectedCycle<Integer> directedCycle = new DirectedCycle<>(graph, DEFAULT_STRATEGY);

    assertThat(directedCycle.hasCycles(), is(false));
    assertThat(directedCycle.getCycles(), emptyIterable());
  }
}
