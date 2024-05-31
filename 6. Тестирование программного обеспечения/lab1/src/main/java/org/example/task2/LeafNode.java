package org.example.task2;

import java.util.stream.Collectors;

class LeafNode extends Node {

    LeafNode(int maxKeys) {
        super(maxKeys);
    }

    @Override
    void insert(int key) {
        int index = 0;
        while (index < keys.size() && keys.get(index) < key) {
            index++;
        }
        keys.add(index, key);
    }

    @Override
    boolean search(int key) {
        return keys.contains(key);
    }

    @Override
    void split(InnerNode parent, int ptr) {
        LeafNode newLeaf = new LeafNode(maxKeys);
        int mid = maxKeys / 2;

        newLeaf.keys.addAll(keys.subList(mid, maxKeys));
        keys.subList(mid, maxKeys).clear();

        parent.keys.add(ptr, newLeaf.keys.get(0));
        parent.children.add(ptr + 1, newLeaf);
    }

    @Override
    public String recursivePrint(String shift) {
        return shift + keys.stream().map(Object::toString).collect(Collectors.joining(" | "));
    }

    @Override
    public String toString() {
        return "[ " + keys.stream().map(Object::toString).collect(Collectors.joining(", ")) + " ]";
    }
}