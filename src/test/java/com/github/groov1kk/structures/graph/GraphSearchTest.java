package com.github.groov1kk.structures.graph;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

public class GraphSearchTest extends BaseGraphTest {

  @Test
  public void testBfsUndirectedGraph() {
    Graph<Integer> graph = new UndirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(3, 6);
    graph.addEdge(3, 7);

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(iterableWithSize(graph.vertexes())));
    assertThat(result, is(equalTo(List.of(1, 2, 3, 4, 5, 6, 7))));
  }

  @Test
  public void testBfsDirectedGraph() {
    Graph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(6, 3);
    graph.addEdge(7, 3);

    List<Integer> result = new LinkedList<>();
    graph.breadthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 2, 3, 4, 5))));
  }

  @Test
  public void testDfsUndirectedGraph() {
    Graph<Integer> graph = new UndirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(3, 6);
    graph.addEdge(3, 7);

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(iterableWithSize(graph.vertexes())));
    assertThat(result, is(equalTo(List.of(1, 3, 7, 6, 2, 5, 4))));
  }

  @Test
  public void testDfsDirectedGraph() {
    Graph<Integer> graph = new DirectedGraph<>();
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(6, 3);
    graph.addEdge(7, 3);

    List<Integer> result = new LinkedList<>();
    graph.depthFirstSearch(1, result::add);

    assertThat(result, is(equalTo(List.of(1, 3, 2, 5, 4))));
  }
}
