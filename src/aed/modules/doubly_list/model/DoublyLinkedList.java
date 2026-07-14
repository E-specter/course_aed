package aed.modules.doubly_list.model;

public class DoublyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T value) {
        if (head == null) { addFirst(value); return; }
        Node<T> newNode = new Node<>(value);
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Índice fuera de rango");
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        Node<T> newNode = new Node<>(value);
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            size--;
            return;
        }
        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public void set(int index, T value) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        current.data = value;
    }

    public int search(T value) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.compareTo(value) == 0) return i;
            current = current.next;
        }
        return -1;
    }

    public void sort() {
        if (size < 2) return;
        for (int i = 0; i < size - 1; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - 1 - i; j++) {
                if (current.data.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
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

    public T[] toArrayReverse() {
        T[] arr = toArray();
        for (int i = 0; i < size / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
        return arr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice fuera de rango");
    }
}
