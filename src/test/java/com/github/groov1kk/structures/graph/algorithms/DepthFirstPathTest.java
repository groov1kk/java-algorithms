package com.github.groov1kk.structures.graph.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.structures.graph.DirectedGraph;

public class DepthFirstPathTest {

  private static final BiPredicate<Integer, Integer> DEFAULT_STRATEGY =
      (e1, e2) -> e1.compareTo(e2) == 0;

  @Test
  public void testHasPath() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);
    graph.addEdge(1, 4);

    DepthFirstPath<Integer> depthFirstPath = new DepthFirstPath<>(graph, 1, DEFAULT_STRATEGY);

    assertThat(depthFirstPath.hasPath(4), is(true));
    assertThat(depthFirstPath.getPath(4), containsInAnyOrder(1, 4));
  }

  @Test
  public void testHasNotPath() {
    DirectedGraph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 5);

    DepthFirstPath<Integer> depthFirstPath = new DepthFirstPath<>(graph, 3, DEFAULT_STRATEGY);

    assertThat(depthFirstPath.hasPath(5), is(false));
    assertThat(depthFirstPath.getPath(5), is(nullValue()));
  }
}
