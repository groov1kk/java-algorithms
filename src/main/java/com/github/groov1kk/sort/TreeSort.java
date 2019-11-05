package com.github.groov1kk.sort;

import com.github.groov1kk.structures.tree.BinarySearchTree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Tree sort algorithm. Transforms the array into a binary search tree and then updates this array
 * with values that are extracted from the tree's nodes by traversing it using In-order (LNR).
 *
 * <p>This sort does not use already implemented {@link BinarySearchTree} because array may contain
 * repeated elements.
 *
 * <p>Sorts in ascending order.
 *
 * <p>Time complexity is O(n*log(n)).
 */
public class TreeSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    Tree tree = Tree.fromArray(array);

    AtomicInteger counter = new AtomicInteger();
    tree.traverse(x -> array[counter.getAndIncrement()] = x.value);
    return array;
  }

  /** Binary search tree implementation. */
  private static class Tree {

    private int value;

    private Tree left;
    private Tree right;

    /**
     * Creates Tree structure. Initial {@code value} will be the root element.
     *
     * @param value Initial value
     */
    private Tree(int value) {
      this.value = value;
    }

    /**
     * Inserts a new value into binary search tree.
     *
     * @param value Value to insert
     */
    void add(int value) {
      if (value < this.value) {
        if (this.left == null) {
          this.left = new Tree(value);
        } else {
          this.left.add(value);
        }
      } else {
        if (this.right == null) {
          this.right = new Tree(value);
        } else {
          this.right.add(value);
        }
      }
    }

    /**
     * Transforms binary search tree from the given {@code array}. The first element of this array
     * (0-indexed) will be the root of this tree.
     *
     * @param array Array to transform
     * @return Binary search tree
     */
    static Tree fromArray(int[] array) {
      Tree tree = new Tree(array[0]);

      for (int i = 1; i < array.length; i++) {
        tree.add(array[i]);
      }
      return tree;
    }

    /**
     * Traverses the binary tree using LNR order.
     *
     * <p>Data from this structure can be read using {@code visitor}.
     *
     * @param visitor Visitor for binary tree's nodes
     */
    void traverse(Consumer<Tree> visitor) {
      if (this.left != null) {
        this.left.traverse(visitor);
      }

      visitor.accept(this);

      if (this.right != null) {
        this.right.traverse(visitor);
      }
    }
  }
}
