package com.ilya.algorithms.structures.queue;

import com.ilya.algorithms.BaseTest;
import com.ilya.algorithms.structures.queue.ArrayQueue;
import com.ilya.algorithms.structures.queue.LinkedQueue;
import com.ilya.algorithms.structures.queue.Queue;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class LinkedQueueTest extends BaseTest {

  @Test
  public void testEmptyQueue() {
    Queue<Object> queue = new LinkedQueue<>();
    Assert.assertThat(queue, emptyIterable());
  }

  @Test
  public void testEnqueueElementQueue() {
    Queue<Object> queue = new LinkedQueue<>();
    queue.enqueue(1);

    Assert.assertThat(queue, iterableWithSize(1));
    Assert.assertThat(queue.size(), is(1));
    Assert.assertThat(queue, hasItem(1));
  }

  @Test
  public void testDequeueElementQueue() {
    Queue<Object> queue = new LinkedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);

    Assert.assertThat(queue, iterableWithSize(2));
    Assert.assertThat(queue, hasItems(1, 2));
    Assert.assertThat(queue.size(), is(2));

    Assert.assertThat(queue.dequeue(), is(1));

    Assert.assertThat(queue, iterableWithSize(1));
    Assert.assertThat(queue, hasItems(2));
    Assert.assertThat(queue.size(), is(1));
  }

  @Test
  public void testEnqueueAllElementQueue() {
    Queue<Object> queue = new LinkedQueue<>();
    queue.enqueueAll(1, 2, 3);

    Assert.assertThat(queue, iterableWithSize(3));
    Assert.assertThat(queue, hasItems(1, 2, 3));
    Assert.assertThat(queue.size(), is(3));
  }

  @Test
  public void testCopyingConstructor() {
    Queue<Integer> queue = ArrayQueue.of(1, 2, 3);
    Queue<Integer> copy = new LinkedQueue<>(queue);

    Assert.assertThat(copy, iterableWithSize(3));
    Assert.assertThat(copy.dequeue(), is(1));
    Assert.assertThat(copy.dequeue(), is(2));
    Assert.assertThat(copy.dequeue(), is(3));
  }
}
