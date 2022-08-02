package com.github.groov1kk.structures.queue;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.groov1kk.BaseTest;

public class ArrayQueueTest extends BaseTest {

  @Test
  public void testEmptyQueue() {
    Queue<Object> queue = new ArrayQueue<>();
    assertThat(queue, emptyIterable());
  }

  @Test
  public void testEnqueueElementQueue() {
    Queue<Object> queue = new ArrayQueue<>();
    queue.enqueue(1);

    assertThat(queue, iterableWithSize(1));
    assertThat(queue.size(), is(1));
    assertThat(queue, hasItem(1));
  }

  @Test
  public void testDequeueElementQueue() {
    Queue<Object> queue = new ArrayQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);

    assertThat(queue, iterableWithSize(2));
    assertThat(queue, hasItems(1, 2));
    assertThat(queue.size(), is(2));

    assertThat(queue.dequeue(), is(1));

    assertThat(queue, iterableWithSize(1));
    assertThat(queue, hasItems(2));
    assertThat(queue.size(), is(1));
  }

  @Test
  public void testEnqueueAllElementQueue() {
    Queue<Object> queue = new ArrayQueue<>();
    queue.enqueueAll(new Integer[] {1, 2, 3});

    assertThat(queue, iterableWithSize(3));
    assertThat(queue, hasItems(1, 2, 3));
    assertThat(queue.size(), is(3));
  }

  @Test
  public void testCopyingConstructor() {
    Queue<Integer> queue = ArrayQueue.of(1, 2, 3);
    Queue<Integer> copy = new ArrayQueue<>(queue);

    assertThat(copy, iterableWithSize(3));
    assertThat(copy.dequeue(), is(1));
    assertThat(copy.dequeue(), is(2));
    assertThat(copy.dequeue(), is(3));
  }
}
