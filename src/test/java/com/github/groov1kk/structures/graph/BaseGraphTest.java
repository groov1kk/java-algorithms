package com.github.groov1kk.structures.graph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;

public abstract class BaseGraphTest {

  /**
   * Verifies, whether the given {@code vertex} of the given {@code graph} contains specific {@code
   * adjacent} vertexes.
   *
   * @param graph Graph to verify vertexes
   * @param vertex Vertex, which adjacent vertexes have to be verified
   * @param expectedSize Expected size of adjacent vertexes
   * @param adjacent Adjacent vertexes
   * @param <V> Vertex type
   */
  @SafeVarargs
  final <V> void assertAdjacent(Graph<V> graph, V vertex, int expectedSize, V... adjacent) {
    Iterable<V> adj = graph.adjacent(vertex);
    assertThat(adj, is(iterableWithSize(expectedSize)));

    if (expectedSize > 0) {
      assertThat(adj, hasItems(adjacent));
    }
  }

  /**
   * Generates undirected graph.
   *
   * @return undirected graph
   */
  protected static Graph<Integer> undirectedGraph() {
    return new UndirectedGraph.Builder<Integer>()
        .addEdge(1, 2)
        .addEdge(1, 3)
        .addEdge(2, 3)
        .addEdge(2, 4)
        .addEdge(2, 5)
        .addEdge(3, 6)
        .addEdge(3, 7)
        .addEdge(9, 10)
        .build();
  }

  /**
   * Generates directed graph
   *
   * @return directed graph
   */
  protected static Graph<Integer> directedGraph() {
    return new DirectedGraph.Builder<Integer>()
        .addEdge(1, 2)
        .addEdge(1, 3)
        .addEdge(2, 4)
        .addEdge(2, 5)
        .addEdge(6, 3)
        .addEdge(7, 3)
        .build();
  }
}
