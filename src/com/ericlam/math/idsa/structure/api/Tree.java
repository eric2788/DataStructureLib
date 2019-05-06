package com.ericlam.math.idsa.structure.api;

import java.util.*;

public abstract class Tree<T extends Comparable> {
    protected Node root;
    private Comparator<T> comparator;


    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public Tree() {
        this.comparator = null;
        this.root = null;
    }

    public List<T> getOrderList(Order type) {
        List<T> list = new ArrayList<>();
        backToTop(this.root);
        order(list, this.root, type);
        return list;
    }

    private void order(List<T> l, Node node, Order type) {
        switch (type) {
            case IN_ORDER:
                if (node != null) {
                    order(l, node.left, Order.IN_ORDER);
                    l.add(node.data);
                    order(l, node.right, Order.IN_ORDER);
                }
                return;
            case PRE_ORDER:
                if (node != null) {
                    l.add(node.data);
                    order(l, node.left, Order.PRE_ORDER);
                    order(l, node.right, Order.PRE_ORDER);
                }
                return;
            case POST_ORDER:
                if (node != null) {
                    order(l, node.left, Order.POST_ORDER);
                    order(l, node.right, Order.POST_ORDER);
                    l.add(node.data);
                }
                break;
        }
    }

    public abstract void add(T data);

    public abstract void add(T[] datas);

    public abstract void add(Collection<T> datas);

    public abstract void print(T node);

    public abstract void printAll();

    public abstract void insert(Node node, T data);

    public abstract void insert(T insert, T data);

    public abstract boolean contain(T data);

    public Node find(T data) {
        backToTop(this.root);
        Node root = this.root;
        while (root != null) {
            if (root.data == data) {
                return root;
            } else {
                if (compare(root.data, data) > 0) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @SuppressWarnings("unchecked")
    protected final int compare(Object k1, Object k2) {
        if (k1 == null) return -1;
        if (k2 == null) return 1;
        return comparator == null ? ((Comparable<T>) k1).compareTo((T) k2)
                : comparator.compare((T) k1, (T) k2);
    }

    public Optional<T> getRootData() {
        backToTop(this.root);
        return Optional.ofNullable(root.data);
    }

    public Optional<T> getLeftChild(T data) {
        Node find = find(data);
        return Optional.ofNullable(find).map(f -> f.left.data);
    }

    public Optional<T> getRightChild(T data) {
        Node find = find(data);
        return Optional.ofNullable(find).map(f -> f.right.data);
    }

    protected void backToTop(Node node) {
        while (node.parent != null) {
            node = node.parent;
        }
    }

    public enum Order {
        PRE_ORDER, IN_ORDER, POST_ORDER
    }

    public class Node {
        public Node left;
        public Node parent;
        public Node right;
        public T data;

        public Node() {
            this.data = null;
        }

        public Node(T data) {
            this.data = data;
        }

        public void printNode() {
            String d = Optional.ofNullable(data).map(a -> a.toString()).orElse("empty");
            String p = Optional.ofNullable(parent).map(a -> a.data.toString()).orElse("empty");
            String l = Optional.ofNullable(left).map(a -> a.data.toString()).orElse("empty");
            String r = Optional.ofNullable(right).map(a -> a.data.toString()).orElse("empty");
            System.out.println("====================");
            System.out.println("Parent: " + p);
            System.out.println("Data: " + d);
            System.out.println("Left-Child: " + l);
            System.out.println("Right-Child: " + r);
            System.out.println("====================");
        }
    }


}
