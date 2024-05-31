package org.example.task2;

public class BPlusTree {
    private Node root;
    private final int maxKeys;

    public BPlusTree(int maxKeys) {
        this.maxKeys = maxKeys;
        this.root = new LeafNode(maxKeys);
    }

    public void insert(int key) {
        root.insert(key);
        if (root.isOverflow()) {
            InnerNode newRoot = new InnerNode(maxKeys);
            newRoot.children.add(root);
            root.split(newRoot, 0);
            root = newRoot;
        }
    }

    public boolean search(int key) {
        return root.search(key);
    }

    public String recursivePrint(){
        return root.recursivePrint("");
    }

    @Override
    public String toString() {
        return this.root.toString();
    }
}
