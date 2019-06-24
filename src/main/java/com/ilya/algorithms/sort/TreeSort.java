package com.ilya.algorithms.sort;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class TreeSort implements Sort {

  @Override
  public int[] sort(int[] array) {
    Tree tree = Tree.fromArray(array);

    AtomicInteger counter = new AtomicInteger();
    tree.traverse(x -> array[counter.getAndIncrement()] = x.value);
    return array;
  }

  private static class Tree {

    private int value;
    private Tree left;
    private Tree right;

    private Tree(int value) {
      this.value = value;
    }

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

    static Tree fromArray(int[] array) {
      Tree tree = new Tree(array[0]);

      for (int i = 1; i < array.length; i++) {
        tree.add(array[i]);
      }
      return tree;
    }

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
