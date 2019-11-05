package com.github.groov1kk.structures.stack;

import com.github.groov1kk.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class LinkedStackTest extends BaseTest {

  @Test
  public void testEmptyStack() {
    Stack<Integer> stack = new LinkedStack<>();
    Assert.assertThat(stack, emptyIterable());
    Assert.assertThat(stack.size(), is(0));
  }

  @Test
  public void testPushElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.push(1);

    Assert.assertThat(stack, iterableWithSize(1));
    Assert.assertThat(stack, hasItem(1));
    Assert.assertThat(stack.size(), is(1));
  }

  @Test
  public void testPopElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.push(1);
    stack.push(2);

    Assert.assertThat(stack, iterableWithSize(2));
    Assert.assertThat(stack, hasItems(1, 2));

    Assert.assertThat(stack.pop(), is(2));

    Assert.assertThat(stack, iterableWithSize(1));
    Assert.assertThat(stack, hasItems(1));
  }

  @Test
  public void testPushAllElementStack() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.pushAll(1, 2, 3);

    Assert.assertThat(stack, iterableWithSize(3));
    Assert.assertThat(stack, hasItems(1, 2, 3));
  }

  @Test
  public void testCopyingConstructor() {
    Stack<Integer> stack = ArrayStack.of(1, 2, 3);
    Stack<Integer> copy = new LinkedStack<>(stack);

    Assert.assertThat(copy, iterableWithSize(3));
    Assert.assertThat(copy.pop(), is(3));
    Assert.assertThat(copy.pop(), is(2));
    Assert.assertThat(copy.pop(), is(1));
    Assert.assertThat(copy.isEmpty(), is(true));
  }
}
