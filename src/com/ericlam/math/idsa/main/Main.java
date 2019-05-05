package com.ericlam.math.idsa.main;

import com.ericlam.math.idsa.structure.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Character> tree = new BinaryTree<>();
        tree.add('D');
        tree.add('B');
        tree.add('F');
        tree.add('A');
        tree.add('C');
        tree.add('H');
        tree.add('G');
        tree.add('E');

        tree.printTree();
        System.out.println(tree.getInOrderList());
        System.out.println(tree.getPostOrderList());
        System.out.println(tree.getPreOrderList());
    }
}
