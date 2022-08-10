package com.github.groov1kk.structures.dsu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.BaseTest;

public class SimpleDisjointSetTest extends BaseTest {

  @Test
  public void testAdd() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);

    assertThat(dsu.size(), is(1));
  }

  @Test
  public void testUnion() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);
    dsu.add(2);
    dsu.add(3);
    dsu.union(1, 3);

    assertThat(dsu.connected(1, 3), is(true));
    assertThat(dsu.connected(1, 2), is(false));
  }

  @Test
  public void testFind() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);

    assertThat(dsu.find(1), is(1));
  }

  @Test
  public void testUnionFind() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);
    dsu.add(2);
    dsu.add(3);
    dsu.add(4);

    dsu.union(1, 3);

    assertThat(dsu.connected(1, 3), is(true));
    assertThat(dsu.find(1), is(3));

    dsu.union(2, 4);

    assertThat(dsu.connected(2, 4), is(true));
    assertThat(dsu.find(2), is(4));

    dsu.union(1, 2);

    assertThat(dsu.connected(1, 4), is(true));
    assertThat(dsu.find(1), is(4));
  }
}
