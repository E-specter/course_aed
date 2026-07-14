package aed.modules.circular_list.model;

public class CircularLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node<T> current = head;
            while (current.next != head) current = current.next;
            current.next = newNode;
            newNode.next = head;
        }
        size++;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Índice fuera de rango");
        if (index == 0) {
            Node<T> newNode = new Node<>(value);
            if (head == null) {
                head = newNode;
                newNode.next = head;
            } else {
                Node<T> last = head;
                while (last.next != head) last = last.next;
                newNode.next = head;
                head = newNode;
                last.next = head;
            }
            size++;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) current = current.next;
        Node<T> newNode = new Node<>(value);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            if (size == 1) { head = null; }
            else {
                Node<T> last = head;
                while (last.next != head) last = last.next;
                head = head.next;
                last.next = head;
            }
            size--;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) current = current.next;
        current.next = current.next.next;
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
        if (head == null) return -1;
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
        head = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Comparable[size];
        if (head == null) return arr;
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice fuera de rango");
    }
}
