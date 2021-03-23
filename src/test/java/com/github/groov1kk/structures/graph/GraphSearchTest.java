package com.github.groov1kk.structures.graph;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class GraphSearchTest extends BaseGraphTest {

  @Test
  public void testBfsUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 2, 3, 4, 5, 6, 7))));
  }

  @Test
  public void testBfsDirectedGraph() {
    Graph<Integer> graph = directedGraph();

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 2, 3, 4, 5))));
  }

  @Test
  public void testDfsUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 3, 7, 6, 2, 5, 4))));
  }

  @Test
  public void testDfsDirectedGraph() {
    Graph<Integer> graph = directedGraph();

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 3, 2, 5, 4))));
  }

  @Test
  public void testHasPathUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    assertThat(graph.hasPath(1, 5), is(true));
    assertThat(graph.hasPath(1, 10), is(false));
  }

  @Test
  public void testHasPathDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    assertThat(graph.hasPath(1, 5), is(true));
    assertThat(graph.hasPath(1, 6), is(false));
  }

  @Test
  public void testPatUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    assertThat(graph.path(1, 6), hasItems(1, 3, 6));
    assertThat(graph.path(1, 10), is(nullValue()));
  }

  @Test
  public void testPatDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    assertThat(graph.path(1, 5), hasItems(1, 2, 5));
    assertThat(graph.path(1, 6), is(nullValue()));
  }

  private Graph<Integer> undirectedGraph() {
    Graph<Integer> graph = new UndirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(3, 6);
    graph.addEdge(3, 7);
    graph.addEdge(9, 10);
    return graph;
  }

  private Graph<Integer> directedGraph() {
    Graph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(6, 3);
    graph.addEdge(7, 3);
    return graph;
  }
}
