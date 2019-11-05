package com.github.groov1kk.structures.dsu;

import com.github.groov1kk.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class SimpleDisjointSetTest extends BaseTest {

  @Test
  public void testAdd() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);

    Assert.assertThat(dsu.size(), is(1));
  }

  @Test
  public void testUnion() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);
    dsu.add(2);
    dsu.add(3);
    dsu.union(1, 3);

    Assert.assertThat(dsu.connected(1, 3), is(true));
    Assert.assertThat(dsu.connected(1, 2), is(false));
  }

  @Test
  public void testFind() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);

    Assert.assertThat(dsu.find(1), is(1));
  }

  @Test
  public void testUnionFind() {
    DisjointSet<Integer> dsu = new SimpleDisjointSet<>();
    dsu.add(1);
    dsu.add(2);
    dsu.add(3);
    dsu.add(4);

    dsu.union(1, 3);

    Assert.assertThat(dsu.connected(1, 3), is(true));
    Assert.assertThat(dsu.find(1), is(3));

    dsu.union(2, 4);

    Assert.assertThat(dsu.connected(2, 4), is(true));
    Assert.assertThat(dsu.find(2), is(4));

    dsu.union(1, 2);

    Assert.assertThat(dsu.connected(1, 4), is(true));
    Assert.assertThat(dsu.find(1), is(4));
  }
}
