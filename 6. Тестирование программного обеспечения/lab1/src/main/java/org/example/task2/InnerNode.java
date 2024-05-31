package org.example.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class InnerNode extends Node {
    List<Node> children;

    InnerNode(int maxKeys) {
        super(maxKeys);
        children = new ArrayList<>();
    }

    @Override
    void insert(int key) {
        int index = 0;
        while (index < keys.size() && keys.get(index) < key) {
            index++;
        }
        children.get(index).insert(key);
        if (children.get(index).isOverflow()) {
            children.get(index).split(this, index);
        }
    }

    @Override
    boolean search(int key) {
        int index = 0;
        while (index < keys.size() && keys.get(index) <= key) {
            index++;
        }
        return children.get(index).search(key);
    }

    @Override
    void split(InnerNode parent, int ptr) {
        InnerNode newNode = new InnerNode(maxKeys);
        int mid = maxKeys / 2;

        newNode.keys.addAll(keys.subList(mid + 1, maxKeys));
        newNode.children.addAll(children.subList(mid + 1, maxKeys + 1));

        parent.keys.add(ptr, keys.get(mid));
        parent.children.add(ptr + 1, newNode);

        keys.subList(mid, maxKeys).clear();
        children.subList(mid + 1, maxKeys + 1).clear();
    }

    @Override
    public String recursivePrint(String shift) {
        StringBuilder result = new StringBuilder(
                shift + keys.stream().map(Object::toString).collect(Collectors.joining(" | "))
        );
        shift = shift.replace("├", "|")
                .replace(">", " ")
                .replace("┕", " ");
        for (int i = 0; i < children.size(); i++){
            result.append("\n");
            if (i != children.size() - 1){
                result.append(children.get(i).recursivePrint(shift + "├> "));
            }else {
                result.append(children.get(i).recursivePrint(shift + "┕> "));
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return children.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
