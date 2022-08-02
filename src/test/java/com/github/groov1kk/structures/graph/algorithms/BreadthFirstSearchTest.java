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

public class BreadthFirstSearchTest extends BaseGraphTest {

  @Test
  public void testBreadthFirstSearchUndirectedGraph() {
    Graph<Integer> graph = undirectedGraph();
    BreadthFirstSearch<Integer> breadthFirstSearch = new BreadthFirstSearch<>(graph);

    List<Integer> result = new LinkedList<>();
    breadthFirstSearch.traverse(1, result::add);

    assertThat(result, is(contains(1, 2, 3, 4, 5, 6, 7)));
    assertThat(result, everyItem(satisfy(breadthFirstSearch::isVisited)));

    assertThat(result, is(not(contains(9, 10))));
    assertThat(ArrayQueue.of(9, 10), everyItem(is(not(satisfy(breadthFirstSearch::isVisited)))));
  }

  @Test
  public void testBreadthFirstSearchDirectedGraph() {
    Graph<Integer> graph = directedGraph();
    BreadthFirstSearch<Integer> breadthFirstSearch = new BreadthFirstSearch<>(graph);

    List<Integer> result = new LinkedList<>();
    breadthFirstSearch.traverse(1, result::add);

    assertThat(result, is(contains(1, 2, 3, 4, 5)));
    assertThat(result, everyItem(satisfy(breadthFirstSearch::isVisited)));

    assertThat(result, is(not(contains(6, 7))));
    assertThat(ArrayQueue.of(6, 7), everyItem(not(satisfy(breadthFirstSearch::isVisited))));
  }
}
