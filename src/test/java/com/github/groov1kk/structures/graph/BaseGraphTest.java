package com.github.groov1kk.structures.graph;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

abstract class BaseGraphTest {

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
}
