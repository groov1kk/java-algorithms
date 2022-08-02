package com.github.groov1kk.structures.tree;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.groov1kk.BaseTest;

public class BinarySearchTreeTest extends BaseTest {

  @Test
  public void testBstInsert() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");

    assertThat(tree.keys(), iterableWithSize(1));
    assertThat(tree.keys(), hasItems(1));

    assertThat(tree.values(), iterableWithSize(1));
    assertThat(tree.values(), hasItems("one"));
  }

  @Test
  public void testBstFind() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");
    tree.insert(2, "two");

    assertThat(tree.find(1), is("one"));
    assertThat(tree.find(2), is("two"));
  }

  @Test
  public void testBstRemove() {
    Tree<Integer, String> tree = new BinarySearchTree<>();
    tree.insert(1, "one");
    tree.insert(2, "two");
    tree.insert(3, "three");

    tree.remove(2);
    assertThat(tree.keys(), iterableWithSize(2));
    assertThat(tree.keys(), hasItems(1, 3));
    assertThat(tree.values(), hasItems("one", "three"));
  }
}
