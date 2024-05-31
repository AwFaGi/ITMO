package org.example.task2;

import java.util.ArrayList;
import java.util.List;

abstract class Node {
    List<Integer> keys;
    protected final int maxKeys;

    Node(int maxKeys) {
        this.maxKeys = maxKeys;
        this.keys = new ArrayList<>();
    }

    public boolean isOverflow() {
        return keys.size() >= maxKeys;
    }

    abstract void insert(int key);

    abstract boolean search(int key);

    abstract void split(InnerNode parent, int ptr);

    public abstract String recursivePrint(String shift);
}