package com.github.groov1kk.structures.graph;

import org.junit.Test;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest extends BaseGraphTest {

  @Test
  public void testAddVertex() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");

    assertThat(graph.vertexes(), is(2));
    assertThat(graph.edges(), is(0));
    assertThat(graph.adjacent("A"), is(emptyIterable()));
    assertThat(graph.adjacent("B"), is(emptyIterable()));
  }

  @Test
  public void testAddTheSameElements() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("A");
    graph.addVertex("B");

    assertThat(graph.vertexes(), is(2));

    graph.addEdge("A", "B");
    graph.addEdge("A", "B");

    assertAdjacent(graph, "A", 2, "B");
    assertAdjacent(graph, "B", 2, "A");
  }

  @Test
  public void testAddEdge() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addEdge("A", "B");
    graph.addEdge("A", "C");

    assertThat(graph.edges(), is(2));

    assertAdjacent(graph, "A", 2, "B", "C");
    assertAdjacent(graph, "B", 1, "A");
    assertAdjacent(graph, "C", 1, "A");
  }

  @Test
  public void testRemoveVertex() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addEdge("A", "B");
    graph.addEdge("A", "A"); // cycle

    assertThat(graph.isAdjacent("A", "B"), is(true));

    graph.removeVertex("A");

    assertThat(graph.vertexes(), is(1));
    assertThat(graph.edges(), is(0));
  }

  @Test
  public void removeEdgeTest() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");

    graph.addEdge("A", "B");
    graph.addEdge("B", "A"); // Parallel
    graph.addEdge("B", "C");
    graph.addEdge("B", "B"); // Cycle

    assertThat(graph.isAdjacent("A", "B"), is(true));
    assertThat(graph.isAdjacent("B", "C"), is(true));
    assertThat(graph.isAdjacent("A", "C"), is(false));

    graph.removeEdge("C", "B");
    assertThat(graph.edges(), is(3));
    assertThat(graph.isAdjacent("C", "B"), is(false));
  }
}
