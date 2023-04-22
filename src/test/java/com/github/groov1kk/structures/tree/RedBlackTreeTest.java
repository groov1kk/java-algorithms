package com.github.groov1kk.structures.tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.iterableWithSize;

import com.github.groov1kk.structures.tree.algorithms.PostorderTreeTraversal;
import com.github.groov1kk.structures.tree.algorithms.PreorderTreeTraversal;
import org.junit.jupiter.api.Test;

public class RedBlackTreeTest {

  @Test
  public void testRbtInsert() {
    Tree<String, String> tree = new RedBlackTree<>();
    tree.insert("e", "E");
    tree.insert("a", "A");
    tree.insert("s", "S");
    tree.insert("r", "R");
    tree.insert("c", "C");

    assertThat(tree.keys(), iterableWithSize(5));
    assertThat(tree.keys(), hasItems("a", "c", "e", "s", "r"));

    assertThat(tree.values(), iterableWithSize(5));
    assertThat(tree.values(), hasItems("A", "C", "E", "S", "R"));
  }

  @Test
  public void testRbtRemove() {
    Tree<String, String> tree = new RedBlackTree<>();
    tree.insert("e", "E");
    tree.insert("a", "A");
    tree.insert("s", "S");
    tree.insert("r", "R");
    tree.insert("c", "C");

    tree.remove("s");

    assertThat(tree.keys(), contains("a", "c", "e", "r"));
    assertThat(tree.values(), contains("A", "C", "E", "R"));
  }

  @Test
  public void testRbtTraversalInjection() {
    PostorderTreeTraversal<String, String> postorderTreeWalk = new PostorderTreeTraversal<>();
    RedBlackTree<String, String> tree = new RedBlackTree<>(postorderTreeWalk);
    tree.insert("e", "E");
    tree.insert("a", "A");
    tree.insert("s", "S");
    tree.insert("r", "R");
    tree.insert("c", "C");

    assertThat(tree.keys(), contains("a", "c", "r", "s", "e"));
    assertThat(tree.values(), contains("A", "C", "R", "S", "E"));

    tree.setTraversal(new PreorderTreeTraversal<>());

    assertThat(tree.keys(), contains("e", "c", "a", "s", "r"));
    assertThat(tree.values(), contains("E", "C", "A", "S", "R"));
  }
}
