package com.ericlam.math.idsa.main;

import com.ericlam.math.idsa.structure.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();

        tree.add('G', 'D', 'B', 'H', 'E', 'A', 'I', 'F', 'J', 'K', 'C');


        tree.printAll();
    }

}
