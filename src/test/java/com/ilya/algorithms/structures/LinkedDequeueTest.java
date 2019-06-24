package com.ilya.algorithms.structures;

import com.ilya.algorithms.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class LinkedDequeueTest extends BaseTest {

  @Test
  public void testEmptyDequeue() {
    Deque<Object> deque = new LinkedDeque<>();

    Assert.assertThat(deque, is(emptyIterable()));
    Assert.assertThat(deque.isEmpty(), is(true));
    Assert.assertThat(deque.size(), is(0));
  }

  @Test
  public void testPushLeft() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);

    Assert.assertThat(deque, is(iterableWithSize(1)));
    Assert.assertThat(deque.size(), is(1));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopLeft() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);

    Assert.assertThat(deque.popLeft(), is(2));
    Assert.assertThat(deque, is(iterableWithSize(1)));
    Assert.assertThat(deque.size(), is(1));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPushRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushRight(1);

    Assert.assertThat(deque, is(iterableWithSize(1)));
    Assert.assertThat(deque.size(), is(1));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushRight(1);
    deque.pushRight(2);

    Assert.assertThat(deque.popRight(), is(2));
    Assert.assertThat(deque, is(iterableWithSize(1)));
    Assert.assertThat(deque.size(), is(1));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPushLeftRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushRight(2);

    Assert.assertThat(deque, is(iterableWithSize(2)));
    Assert.assertThat(deque.size(), is(2));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopLeftRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);
    deque.pushRight(3);
    deque.pushRight(4);

    Assert.assertThat(deque.popLeft(), is(2));
    Assert.assertThat(deque, is(iterableWithSize(3)));
    Assert.assertThat(deque.size(), is(3));
    Assert.assertThat(deque.isEmpty(), is(false));

    Assert.assertThat(deque.popRight(), is(4));
    Assert.assertThat(deque, is(iterableWithSize(2)));
    Assert.assertThat(deque.size(), is(2));
    Assert.assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testCopyingConstructor() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);
    deque.pushRight(3);
    deque.pushRight(4);

    Deque<Integer> copy = new LinkedDeque<>(deque);

    Assert.assertThat(copy, is(iterableWithSize(4)));
    Assert.assertThat(copy.popLeft(), is(2));
    Assert.assertThat(copy.popLeft(), is(1));
    Assert.assertThat(copy.popLeft(), is(3));
    Assert.assertThat(copy.popLeft(), is(4));
  }
}
