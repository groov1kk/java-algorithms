package com.github.groov1kk.structures.graph.algorithms;

import static com.github.groov1kk.matchers.IsSatisfy.satisfy;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.github.groov1kk.structures.graph.BaseGraphTest;
import com.github.groov1kk.structures.graph.Graph;
import com.github.groov1kk.structures.queue.ArrayQueue;

public class DepthFirstSearchTest extends BaseGraphTest {

  @Test
  public void testDepthFirstSearchUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    DepthFirstSearch<Integer> depthFirstSearch = new DepthFirstSearch<>(graph);

    List<Integer> result = new LinkedList<>();
    depthFirstSearch.traverse(1, result::add);

    assertThat(result, is(contains(1, 3, 7, 6, 2, 5, 4)));
    assertThat(result, everyItem(satisfy(depthFirstSearch::isVisited)));

    assertThat(result, is(not(contains(9, 10))));
    assertThat(ArrayQueue.of(9, 10), everyItem(is(not(satisfy(depthFirstSearch::isVisited)))));
  }

  @Test
  public void testDepthFirstSearchDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    DepthFirstSearch<Integer> depthFirstSearch = new DepthFirstSearch<>(graph);

    List<Integer> result = new LinkedList<>();
    depthFirstSearch.traverse(1, result::add);

    assertThat(result, is(contains(1, 3, 2, 5, 4)));
    assertThat(result, everyItem(satisfy(depthFirstSearch::isVisited)));

    assertThat(result, is(not(contains(6, 7))));
    assertThat(ArrayQueue.of(6, 7), everyItem(is(not(satisfy(depthFirstSearch::isVisited)))));
  }
}
