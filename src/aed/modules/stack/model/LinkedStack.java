package aed.modules.stack.model;

public class LinkedStack<T extends Comparable<T>> {
    private Node<T> top;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack vacío");
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack vacío");
        return top.data;
    }

    public int search(T value) {
        Node<T> current = top;
        for (int i = 0; i < size; i++) {
            if (current.data.compareTo(value) == 0) return i;
            current = current.next;
        }
        return -1;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void clear() {
        top = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Comparable[size];
        Node<T> current = top;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }
}
