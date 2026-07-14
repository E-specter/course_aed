package aed.modules.bst.model;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public static class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;
        public Node(T data) { this.data = data; }
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) node.left = insert(node.left, value);
        else if (cmp > 0) node.right = insert(node.right, value);
        return node;
    }

    public boolean search(T value) {
        return search(root, value);
    }

    private boolean search(Node<T> node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.data);
        if (cmp == 0) return true;
        return cmp < 0 ? search(node.left, value) : search(node.right, value);
    }

    public boolean delete(T value) {
        if (!search(value)) return false;
        root = delete(root, value);
        size--;
        return true;
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<T> successor = findMin(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    private void inOrder(Node<T> node, StringBuilder sb) {
        if (node == null) return;
        inOrder(node.left, sb);
        sb.append(node.data).append(" ");
        inOrder(node.right, sb);
    }

    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString().trim();
    }

    private void preOrder(Node<T> node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.data).append(" ");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString().trim();
    }

    private void postOrder(Node<T> node, StringBuilder sb) {
        if (node == null) return;
        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.data).append(" ");
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Node<Integer> getRoot() { return (Node<Integer>) root; }
}
