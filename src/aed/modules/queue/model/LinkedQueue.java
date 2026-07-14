package aed.modules.queue.model;

public class LinkedQueue<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue vacío");
        T data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue vacío");
        return head.data;
    }

    public int search(T value) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.compareTo(value) == 0) return i;
            current = current.next;
        }
        return -1;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Comparable[size];
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }
}
