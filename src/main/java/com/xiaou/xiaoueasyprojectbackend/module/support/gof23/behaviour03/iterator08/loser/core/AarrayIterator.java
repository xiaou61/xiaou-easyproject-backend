package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.iterator08.loser.core;

import java.util.NoSuchElementException;

public class AarrayIterator<T> implements Iterable<T>, Iterator<T> {

    protected Object[] elementData;

    protected int index = 0;
    private int curLength;
    private int curIndex;

    public AarrayIterator() {
        int initialCapacity = 10;
        elementData = new Object[initialCapacity];
        curLength = initialCapacity;
    }

    @Override
    public Boolean hasNext() {
        return index < curIndex;
    }

    @Override
    public T next() {
        if (hasNext()) {
            Object elementDatum = elementData[index++];
            return (T) elementDatum;
        }
        throw new NoSuchElementException("only " + elementData.length + " elements");
    }

    public void add(T data) {
        if (index >= curLength) {
            int length = curLength + (curLength >> 1);
            Object[] objects = new Object[length];
            curLength = length;
            System.arraycopy(elementData, 0, objects, 0, index);
            elementData = objects;
        }
        elementData[index++] = data;
        curIndex++;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public Iterator<T> iterator() {
        index = 0;
        return this;
    }

}