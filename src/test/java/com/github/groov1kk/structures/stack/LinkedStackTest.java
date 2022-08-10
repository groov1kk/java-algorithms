package com.github.groov1kk.structures.stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.BaseTest;

public class LinkedStackTest extends BaseTest {

  @Test
  public void testEmptyStack() {
    Stack<Integer> stack = new LinkedStack<>();
    assertThat(stack, emptyIterable());
    assertThat(stack.size(), is(0));
  }

  @Test
  public void testPushElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.push(1);

    assertThat(stack, iterableWithSize(1));
    assertThat(stack, hasItem(1));
    assertThat(stack.size(), is(1));
  }

  @Test
  public void testPopElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.push(1);
    stack.push(2);

    assertThat(stack, iterableWithSize(2));
    assertThat(stack, hasItems(1, 2));

    assertThat(stack.pop(), is(2));

    assertThat(stack, iterableWithSize(1));
    assertThat(stack, hasItems(1));
  }

  @Test
  public void testPushAllElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.pushAll(new Integer[] {1, 2, 3});

    assertThat(stack, iterableWithSize(3));
    assertThat(stack, hasItems(1, 2, 3));
  }

  @Test
  public void testCopyingConstructor() {
    Stack<Integer> stack = ArrayStack.of(1, 2, 3);
    Stack<Integer> copy = new LinkedStack<>(stack);

    assertThat(copy, iterableWithSize(3));
    assertThat(copy.pop(), is(3));
    assertThat(copy.pop(), is(2));
    assertThat(copy.pop(), is(1));
    assertThat(copy.isEmpty(), is(true));
  }
}
