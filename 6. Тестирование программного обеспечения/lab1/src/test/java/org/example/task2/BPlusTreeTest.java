package org.example.task2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class BPlusTreeTest {

    @Test
    void testSearch() {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 10; i <= 40; i++){
            data.add(i);
        }
        Collections.shuffle(data, new Random(42));

        BPlusTree tree = new BPlusTree(6);
        for (int i : data) {
            tree.insert(i);
        }

        for (int i : data) {
            assertTrue(tree.search(i));
        }

        for (int i = 0; i < 10; i++){
            assertFalse(tree.search(i));
        }

        for (int i = 41; i < 100; i++){
            assertFalse(tree.search(i));
        }
    }

    @Test
    void testConsecutive(){
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 10; i <= 30; i++){
            data.add(i);
        }

        BPlusTree tree1 = new BPlusTree(6);
        for (int i : data) {
            tree1.insert(i);
        }

        assertEquals(
            "[ 10, 11, 12 ], [ 13, 14, 15 ], [ 16, 17, 18 ], [ 19, 20, 21 ], [ 22, 23, 24 ], [ 25, 26, 27 ], [ 28, 29, 30 ]",
            tree1.toString()
        );

        assertEquals("22\n" +
                "├> 13 | 16 | 19\n" +
                "|  ├> 10 | 11 | 12\n" +
                "|  ├> 13 | 14 | 15\n" +
                "|  ├> 16 | 17 | 18\n" +
                "|  ┕> 19 | 20 | 21\n" +
                "┕> 25 | 28\n" +
                "   ├> 22 | 23 | 24\n" +
                "   ├> 25 | 26 | 27\n" +
                "   ┕> 28 | 29 | 30",
                tree1.recursivePrint()
        );
    }

    @Test
    void testShuffled(){
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 10; i <= 30; i++){
            data.add(i);
        }

        Collections.shuffle(data, new Random(42));

        BPlusTree tree1 = new BPlusTree(6);
        for (int i : data) {
            tree1.insert(i);
        }

        assertEquals(
                "[ 10, 11, 12 ], [ 13, 14, 15 ], [ 16, 17, 18, 19 ], [ 20, 21, 22 ], [ 23, 24, 25, 26 ], [ 27, 28, 29, 30 ]",
                tree1.toString()
        );

        assertEquals("13 | 16 | 20 | 23 | 27\n" +
                        "├> 10 | 11 | 12\n" +
                        "├> 13 | 14 | 15\n" +
                        "├> 16 | 17 | 18 | 19\n" +
                        "├> 20 | 21 | 22\n" +
                        "├> 23 | 24 | 25 | 26\n" +
                        "┕> 27 | 28 | 29 | 30",
                tree1.recursivePrint()
        );
    }

    @Test
    void testPartOfShuffled(){
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 1; i <= 100; i++){
            data.add(i);
        }

        Collections.shuffle(data, new Random(42));

        BPlusTree tree1 = new BPlusTree(6);
        for (int i : data.subList(0, 30)) {
            tree1.insert(i);
        }

        assertEquals(
                "[ 4, 7, 9, 11 ], [ 13, 14, 22 ], [ 25, 37, 38 ], [ 42, 46, 47 ], [ 52, 55, 56, 58, 61 ], [ 64, 65, 70, 71 ], [ 72, 82, 88 ], [ 90, 92, 93, 98, 99 ]",
                tree1.toString()
        );

        assertEquals("64\n" +
                        "├> 13 | 25 | 42 | 52\n" +
                        "|  ├> 4 | 7 | 9 | 11\n" +
                        "|  ├> 13 | 14 | 22\n" +
                        "|  ├> 25 | 37 | 38\n" +
                        "|  ├> 42 | 46 | 47\n" +
                        "|  ┕> 52 | 55 | 56 | 58 | 61\n" +
                        "┕> 72 | 90\n" +
                        "   ├> 64 | 65 | 70 | 71\n" +
                        "   ├> 72 | 82 | 88\n" +
                        "   ┕> 90 | 92 | 93 | 98 | 99",
                tree1.recursivePrint()
        );
    }
}

