package com.ilya.algorithms.structures;

public interface Deque<E> extends Iterable<E> {

    void pushLeft(E element);

    void pushRight(E element);

    E popLeft();

    E popRight();

    int size();

    boolean isEmpty();
}
