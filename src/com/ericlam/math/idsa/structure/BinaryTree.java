package com.ericlam.math.idsa.structure;

import java.util.*;

public class BinaryTree<T extends Comparable>{
    private Node root;
    private Comparator<T> comparator;

    private class Node{
        private Node left;
        private Node right;
        private Node parent;
        private T data;

        private Node(){
            this.data = null;
        }

        private Node(T data) {
            this.data = data;
        }

        private void printNode(){
            System.out.println("====================");
            Optional.ofNullable(data).ifPresentOrElse(a->System.out.println("Data: "+a.toString()),()->System.out.println("Data: null"));
            Optional.ofNullable(parent).map(a->a.data).ifPresentOrElse(a->System.out.println("Parent: "+a.toString()),()->System.out.println("Parent: null"));
            Optional.ofNullable(left).map(a->a.data).ifPresentOrElse(a->System.out.println("Left: "+a.toString()),()->System.out.println("Left: null"));
            Optional.ofNullable(right).map(a->a.data).ifPresentOrElse(a->System.out.println("Right: "+a.toString()),()->System.out.println("Right: null"));
            System.out.println("====================");
        }
    }

    public BinaryTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public BinaryTree() {
        this.root = null;
        this.comparator = null;
    }

    @SuppressWarnings("unchecked")
    public void apply(T... array){
        Arrays.sort(array);
        List<T> list = new LinkedList<>();
        int split = Math.round(array.length/2);
        while (split > 1){
            list.add(array[split-1]);
            split = Math.round(split/2);
        }



    }

    public void add(T data){
        Node tmp = new Node(data);
        tmp.left = tmp.right = null;
        if (root == null){
            root = tmp;
        }else{
            insert(data, root, tmp);
        }
        backToTop(this.root);
    }

    public void add(T parent, T data){
        Node root;
        if (compare(data,parent) > 1){
            System.out.println("You can't insert a data that is bigger than parent!");
            root = this.root;
        }else{
            root = find(parent);
        }
        if (root == null) {
            System.out.println("[ERROR] No "+parent+" in this BinaryTree.");
            root = this.root;
        }
        Node tmp = new Node(data);
        tmp.left = tmp.right = null;
        insert(data, root, tmp);
    }

    private void insert(T data, Node root, Node tmp) {
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

    boolean contain(T data){
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

    public void printTree(){
        backToTop(this.root);
        print(root);
    }

    public List<T> getInOrderList(){
        List<T> list = new ArrayList<>();
        backToTop(this.root);
        inOrder(list,this.root);
        return list;
    }

    public List<T> getPostOrderList(){
        List<T> list = new ArrayList<>();
        backToTop(this.root);
        postOrder(list,this.root);
        return list;
    }

    public List<T> getPreOrderList(){
        List<T> list = new ArrayList<>();
        backToTop(this.root);
        preOrder(list,this.root);
        return list;
    }

    private void inOrder(List<T> l, Node node){
        if (node != null){
            inOrder(l,node.left);
            l.add(node.data);
            inOrder(l,node.right);
        }
    }

    private void postOrder(List<T> l, Node node){
        if (node != null){
            postOrder(l,node.left);
            postOrder(l,node.right);
            l.add(node.data);
        }
    }

    private void preOrder(List<T> l, Node node){
        if (node != null){
            l.add(node.data);
            preOrder(l,node.left);
            preOrder(l,node.right);
        }
    }

    private Node find(T data){
        backToTop(this.root);
        Node root = this.root;
        while (root != null){
            if (root.data == data){
                return root;
            }else{
                if (compare(root.data,data) > 0){
                    root = root.left;
                }else{
                    root = root.right;
                }
            }
        }
        return null;
    }

    private void backToTop(Node node){
        while (node.parent != null){
            node = node.parent;
        }
    }

    public T getRootData(){
        backToTop(this.root);
        return root.data;
    }

    public T getLeftChild(T data){
        Node find = find(data);
        if (find == null || find.left == null) return null;
        return find.left.data;
    }

    public T getRightChild(T data){
        Node find = find(data);
        if (find == null || find.right == null) return null;
        return find.right.data;
    }





    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2) {
        if (k1 == null) return -1;
        if (k2 == null) return 1;
        return comparator==null ? ((Comparable<T>)k1).compareTo((T)k2)
                : comparator.compare((T)k1, (T)k2);
    }

}
