package com.github.groov1kk.structures.tree;

import com.github.groov1kk.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.iterableWithSize;

public class RedBlackTreeTest extends BaseTest {

  @Test
  public void testRbtInsert() {
    Tree<String, String> tree = new RedBlackTree<>();
    tree.insert("e", "E");
    tree.insert("a", "A");
    tree.insert("s", "S");
    tree.insert("r", "R");
    tree.insert("c", "C");

    Assert.assertThat(tree.keys(), iterableWithSize(5));
    Assert.assertThat(tree.keys(), hasItems("a", "c", "e", "s", "r"));

    Assert.assertThat(tree.values(), iterableWithSize(5));
    Assert.assertThat(tree.values(), hasItems("A", "C", "E", "S", "R"));
  }
}
