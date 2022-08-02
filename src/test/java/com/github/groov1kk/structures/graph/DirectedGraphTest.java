package com.github.groov1kk.structures.graph;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

public class DirectedGraphTest extends BaseGraphTest {

  @Test
  public void testAddVertex() {
    Graph<String> graph = new DirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");

    assertThat(graph.vertices(), is(2));
    assertThat(graph.edges(), is(0));
    assertThat(graph.adjacent("A"), is(emptyIterable()));
    assertThat(graph.adjacent("B"), is(emptyIterable()));
  }

  @Test
  public void testAddTheSameElements() {
    Graph<String> graph = new DirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("A");
    graph.addVertex("B");

    assertThat(graph.vertices(), is(2));

    graph.addEdge("A", "B");
    graph.addEdge("A", "B");

    assertAdjacent(graph, "A", 2, "B");
    assertAdjacent(graph, "B", 0);
  }

  @Test
  public void testAddEdge() {
    Graph<String> graph = new DirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addEdge("A", "B");
    graph.addEdge("A", "C");

    assertThat(graph.edges(), is(2));

    assertAdjacent(graph, "A", 2, "B", "C");
    assertAdjacent(graph, "B", 0);
    assertAdjacent(graph, "C", 0);
  }

  @Test
  public void testRemoveVertex() {
    Graph<String> graph = new DirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");

    graph.addEdge("A", "B");
    graph.addEdge("B", "A");
    graph.addEdge("B", "C");
    graph.addEdge("B", "B"); // Cycle

    assertThat(graph.isAdjacent("A", "B"), is(true));
    assertThat(graph.isAdjacent("B", "C"), is(true));
    assertThat(graph.isAdjacent("A", "C"), is(false));

    graph.removeVertex("B");

    assertThat(graph.vertices(), is(2));
    assertThat(graph.edges(), is(0));
  }

  @Test
  public void testRemoveEdge() {
    Graph<String> graph = new DirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");

    graph.addEdge("A", "B");
    graph.addEdge("B", "A");
    graph.addEdge("B", "C");
    graph.addEdge("B", "B"); // Cycle

    assertThat(graph.isAdjacent("A", "B"), is(true));
    assertThat(graph.isAdjacent("B", "C"), is(true));
    assertThat(graph.isAdjacent("A", "C"), is(false));

    graph.removeEdge("A", "B");
    assertThat(graph.edges(), is(3));
    assertThat(graph.isAdjacent("A", "B"), is(false));
  }

  @Test
  public void testHasCycles() {
    DirectedGraph<String> graph = new DirectedGraph<>();
    graph.addEdge("A", "B");
    graph.addEdge("B", "C");
    graph.addEdge("C", "D");
    graph.addEdge("D", "B");
    graph.addEdge("D", "A");
    graph.addEdge("X", "Y");

    assertThat(graph.hasCycles(), is(true));
    assertThat(graph.hasCycles("A"), is(true));
    assertThat(graph.hasCycles("B"), is(true));
    assertThat(graph.hasCycles("X"), is(false));
  }

  @Test
  public void testGetCycles() {
    DirectedGraph<String> graph = new DirectedGraph<>();
    graph.addEdge("A", "B");
    graph.addEdge("B", "C");
    graph.addEdge("C", "D");
    graph.addEdge("D", "B");
    graph.addEdge("D", "A");
    graph.addEdge("X", "Y");

    @SuppressWarnings("unchecked")
    Matcher<Iterable<Iterable<String>>> matcher =
        hasItems(containsInAnyOrder("A", "B", "C", "D"), containsInAnyOrder("B", "C", "D"));

    assertThat(graph.getCycles(), matcher);
    assertThat(graph.getCycles("A"), matcher);
    assertThat(graph.getCycles("B"), matcher);
    assertThat(graph.getCycles("X"), is(emptyIterable()));
  }

  @Test
  public void testTopologicalSort() {
    DirectedGraph<Integer> graph =
        new DirectedGraph.Builder<Integer>()
            .addEdge(0, 5)
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(3, 6)
            .addEdge(3, 5)
            .addEdge(3, 4)
            .addEdge(5, 2)
            .addEdge(6, 4)
            .addEdge(6, 0)
            .addEdge(3, 2)
            .addEdge(1, 4)
            .build();

    assertThat(graph.topologicalSort(0), is(hasItems(4, 1, 2, 5, 0)));
  }

  @Test
  public void testToString() {
    DirectedGraph<String> graph = new DirectedGraph<>();
    graph.addEdge("A", "B");
    graph.addEdge("C", "D");

    assertThat(graph.toString(), is(equalTo("[[A, B], [B], [C, D], [D]]")));

    graph.addEdge("B", "D");

    assertThat(graph.toString(), is(equalTo("[[A, B, D], [B, D], [C, D], [D]]")));
  }

  @Test
  public void testBfsDirectedGraph() {
    Graph<Integer> graph = directedGraph();

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(contains(1, 2, 3, 4, 5)));
  }

  @Test
  public void testDfsDirectedGraph() {
    Graph<Integer> graph = directedGraph();

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(contains(1, 3, 2, 5, 4)));
  }

  @Test
  public void testHasPathDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    assertThat(graph.hasPath(1, 5), is(true));
    assertThat(graph.hasPath(1, 6), is(false));
  }

  @Test
  public void testPathDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    assertThat(graph.getPath(1, 5), hasItems(1, 2, 5));
    assertThat(graph.getPath(1, 6), is(nullValue()));
  }
}
