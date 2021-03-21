package com.github.groov1kk.structures.stack;

import com.github.groov1kk.BaseTest;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

public class ArrayStackTest extends BaseTest {

  @Test
  public void testEmptyStack() {
    Stack<Object> stack = new ArrayStack<>();
    assertThat(stack, emptyIterable());
  }

  @Test
  public void testPushElementStack() {
    Stack<Integer> stack = new ArrayStack<>();
    stack.push(1);

    assertThat(stack, iterableWithSize(1));
    assertThat(stack, hasItem(1));
  }

  @Test
  public void testPopElementStack() {
    Stack<Integer> stack = new ArrayStack<>();
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
    Stack<Integer> stack = new ArrayStack<>();
    stack.pushAll(1, 2, 3);

    assertThat(stack, iterableWithSize(3));
    assertThat(stack, hasItems(1, 2, 3));
  }

  @Test
  public void testCopyingConstructor() {
    Stack<Integer> stack = ArrayStack.of(1, 2, 3);
    Stack<Integer> copy = new ArrayStack<>(stack);

    assertThat(copy, iterableWithSize(3));
    assertThat(copy.pop(), is(3));
    assertThat(copy.pop(), is(2));
    assertThat(copy.pop(), is(1));
  }
}
