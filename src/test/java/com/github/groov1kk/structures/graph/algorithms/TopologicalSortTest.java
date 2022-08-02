package com.github.groov1kk.structures.graph.algorithms;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.groov1kk.structures.graph.DirectedGraph;

public class TopologicalSortTest {

  @Test
  public void testTopologicalSort() {
    DirectedGraph<Integer> graph =
        new DirectedGraph.Builder<Integer>()
            .addEdge(5, 11)
            .addEdge(7, 11)
            .addEdge(11, 2)
            .addEdge(7, 8)
            .addEdge(3, 8)
            .addEdge(8, 9)
            .addEdge(3, 10)
            .addEdge(11, 9)
            .addEdge(11, 10)
            .build();

    TopologicalSort<Integer> topologicalSort = new TopologicalSort<>(graph);

    assertThat(topologicalSort.isDag(), is(true));
    assertThat(topologicalSort.sorted(), contains(7, 5, 11, 3, 8, 9, 10, 2));
  }

  @Test
  public void testTopologicalSortWithCycles() {

  }
}
