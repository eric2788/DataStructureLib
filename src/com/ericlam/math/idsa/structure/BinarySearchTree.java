package com.ericlam.math.idsa.structure;

import com.ericlam.math.idsa.structure.api.Tree;

import java.util.Collection;

public class BinarySearchTree<T extends Comparable> extends Tree<T> {

    @Override
    public void add(T data){
        Node tmp = new Node(data);
        tmp.left = tmp.right = null;
        if (root == null){
            root = tmp;
        }else{
            ins(data, root, tmp);
        }
        backToTop(this.root);
    }

    @Override
    @SafeVarargs
    public final void add(T... datas) {
        for (T t : datas) {
            add(t);
        }
    }

    @Override
    public void add(Collection<T> datas) {
        for (T t : datas) {
            add(t);
        }
    }

    @Override
    public void print(T node) {
        Node n = find(node);
        if (n == null) {
            System.out.println("No this data");
            return;
        }
        print(n);
    }

    @Override
    public void printAll() {
        backToTop(this.root);
        print(root);
    }

    @Override
    public void insert(Node node, T data) {
        Node tmp = new Node(data);
        tmp.left = tmp.right = null;
        ins(data, node, tmp);
    }

    @Override
    public void insert(T insert, T data) {
        Node root;
        if (compare(data, insert) > 1) {
            System.out.println("You can't ins a data that is bigger than parent!");
            root = this.root;
        }else{
            root = find(insert);
        }
        if (root == null) {
            System.out.println("[ERROR] No " + insert + " in this BinaryTree.");
            root = this.root;
        }
        Node tmp = new Node(data);
        tmp.left = tmp.right = null;
        ins(data, root, tmp);
    }

    private void ins(T data, Node root, Node tmp) {
        while (root.left != tmp && root.right != tmp){
            if (compare(root.data,data) > 0){
                if (root.left == null) {
                    root.left = tmp;
                    tmp.parent = root;
                }
                else root = root.left;
            }else{
                if (root.right == null) {
                    root.right = tmp;
                    tmp.parent = root;
                }
                else root = root.right;
            }
        }
    }

    @Override
    public boolean contain(T data) {
        boolean found = false;
        backToTop(this.root);
        Node root = this.root;
        while (root != null && !found){
            if (root.data == data){
                found = true;
            }else{
                if (compare(root.data,data) > 0){
                    root = root.left;
                }else{
                    root = root.right;
                }
            }
        }
        return found;
    }

    public String bit(T data){
        String bit = "";
        backToTop(this.root);
        Node root = this.root;
        while (root != null){
            if (root.data == data){
                return bit.isBlank() ? "root" : bit;
            }else{
                if (compare(root.data,data) > 0){
                    root = root.left;
                    bit = bit.concat("0");
                }else{
                    root = root.right;
                    bit = bit.concat("1");
                }
            }
        }
        return "No this data";
    }

    private void print(Node node){
        if (node == null) return;
        node.printNode();
        if (node.left != null || node.right != null){
            print(node.left);
            print(node.right);
        }
    }



}
