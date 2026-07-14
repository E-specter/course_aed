package aed.modules.binary_tree.model;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public void insert(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            if (current.left == null) {
                current.left = newNode;
                size++;
                return;
            }
            queue.add(current.left);
            if (current.right == null) {
                current.right = newNode;
                size++;
                return;
            }
            queue.add(current.right);
        }
    }

    public boolean search(T value) {
        if (root == null) return false;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            if (current.data.compareTo(value) == 0) return true;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return false;
    }

    public boolean delete(T value) {
        if (root == null) return false;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        TreeNode<T> target = null;
        TreeNode<T> deepest = null;
        TreeNode<T> deepestParent = null;

        while (!queue.isEmpty()) {
            deepest = queue.poll();
            if (deepest.data.compareTo(value) == 0) target = deepest;
            if (deepest.left != null) {
                deepestParent = deepest;
                queue.add(deepest.left);
            }
            if (deepest.right != null) {
                deepestParent = deepest;
                queue.add(deepest.right);
            }
        }

        if (target == null) return false;

        target.data = deepest.data;
        if (deepestParent != null) {
            if (deepestParent.left == deepest) deepestParent.left = null;
            else deepestParent.right = null;
        } else {
            root = null;
        }
        size--;
        return true;
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    private void inOrder(TreeNode<T> node, StringBuilder sb) {
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

    private void preOrder(TreeNode<T> node, StringBuilder sb) {
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

    private void postOrder(TreeNode<T> node, StringBuilder sb) {
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
    public TreeNode<T> getRoot() { return root; }
}
