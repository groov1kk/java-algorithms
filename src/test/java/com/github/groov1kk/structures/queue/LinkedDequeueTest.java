package com.github.groov1kk.structures.queue;

import com.github.groov1kk.BaseTest;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

public class LinkedDequeueTest extends BaseTest {

  @Test
  public void testEmptyDequeue() {
    Deque<Object> deque = new LinkedDeque<>();

    assertThat(deque, is(emptyIterable()));
    assertThat(deque.isEmpty(), is(true));
    assertThat(deque.size(), is(0));
  }

  @Test
  public void testPushLeft() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);

    assertThat(deque, is(iterableWithSize(1)));
    assertThat(deque.size(), is(1));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopLeft() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);

    assertThat(deque.popLeft(), is(2));
    assertThat(deque, is(iterableWithSize(1)));
    assertThat(deque.size(), is(1));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPushRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushRight(1);

    assertThat(deque, is(iterableWithSize(1)));
    assertThat(deque.size(), is(1));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushRight(1);
    deque.pushRight(2);

    assertThat(deque.popRight(), is(2));
    assertThat(deque, is(iterableWithSize(1)));
    assertThat(deque.size(), is(1));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPushLeftRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushRight(2);

    assertThat(deque, is(iterableWithSize(2)));
    assertThat(deque.size(), is(2));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testPopLeftRight() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);
    deque.pushRight(3);
    deque.pushRight(4);

    assertThat(deque.popLeft(), is(2));
    assertThat(deque, is(iterableWithSize(3)));
    assertThat(deque.size(), is(3));
    assertThat(deque.isEmpty(), is(false));

    assertThat(deque.popRight(), is(4));
    assertThat(deque, is(iterableWithSize(2)));
    assertThat(deque.size(), is(2));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void testCopyingConstructor() {
    Deque<Integer> deque = new LinkedDeque<>();
    deque.pushLeft(1);
    deque.pushLeft(2);
    deque.pushRight(3);
    deque.pushRight(4);

    Deque<Integer> copy = new LinkedDeque<>(deque);

    assertThat(copy, is(iterableWithSize(4)));
    assertThat(copy.popLeft(), is(2));
    assertThat(copy.popLeft(), is(1));
    assertThat(copy.popLeft(), is(3));
    assertThat(copy.popLeft(), is(4));
  }
}
