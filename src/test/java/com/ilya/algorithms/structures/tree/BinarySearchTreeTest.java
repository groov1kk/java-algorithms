package com.ilya.algorithms.structures.tree;

import com.ilya.algorithms.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class BinarySearchTreeTest extends BaseTest {

  @Test
  public void testBstInsert() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");

    Assert.assertThat(tree.keys(), iterableWithSize(1));
    Assert.assertThat(tree.keys(), hasItems(1));

    Assert.assertThat(tree.values(), iterableWithSize(1));
    Assert.assertThat(tree.values(), hasItems("one"));
  }

  @Test
  public void testBstFind() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");
    tree.insert(2, "two");

    Assert.assertThat(tree.find(1), is("one"));
    Assert.assertThat(tree.find(2), is("two"));
  }

  @Test
  public void testBstRemove() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");
    tree.insert(2, "two");
    tree.insert(3, "three");

    tree.remove(2);
    Assert.assertThat(tree.keys(), iterableWithSize(2));
    Assert.assertThat(tree.keys(), hasItems(1, 3));
    Assert.assertThat(tree.values(), hasItems("one", "three"));
  }
}
