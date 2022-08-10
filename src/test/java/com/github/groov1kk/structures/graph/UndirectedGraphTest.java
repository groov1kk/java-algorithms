package com.github.groov1kk.structures.graph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UndirectedGraphTest extends BaseGraphTest {

  @Test
  public void testAddVertex() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");

    assertThat(graph.vertices(), is(2));
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

    assertThat(graph.vertices(), is(2));

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

    assertThat(graph.vertices(), is(1));
    assertThat(graph.edges(), is(0));
  }

  @Test
  public void testRemoveEdge() {
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

  @Test
  public void testToString() {
    Graph<String> graph = new UndirectedGraph<>();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");

    graph.addEdge("A", "B");
    graph.addEdge("C", "D");

    assertThat(graph.toString(), is(equalTo("[[A, B], [C, D]]")));

    graph.addEdge("B", "D");

    assertThat(graph.toString(), is(equalTo("[[A, B, D, C]]")));
  }

  @Test
  public void testBfsUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(contains(1, 2, 3, 4, 5, 6, 7)));
  }

  @Test
  public void testDfsUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(contains(1, 3, 7, 6, 2, 5, 4)));
  }

  @Test
  public void testHasPathUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    assertThat(graph.hasPath(1, 5), is(true));
    assertThat(graph.hasPath(1, 10), is(false));
  }

  @Test
  public void testPathUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    assertThat(graph.getPath(1, 6), hasItems(1, 3, 6));
    assertThat(graph.getPath(1, 10), is(nullValue()));
  }
}
