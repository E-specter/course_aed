package aed.modules.arrays_1d.model;

public class Array1D {
    private int[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Array1D() {
        this(DEFAULT_CAPACITY);
    }

    public Array1D(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacidad debe ser > 0");
        this.data = new int[capacity];
        this.size = 0;
    }

    public void add(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    public void insert(int index, int value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Índice fuera de rango");
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    public void set(int index, int value) {
        checkIndex(index);
        data[index] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void delete(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    public int linearSearch(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) return i;
        }
        return -1;
    }

    public int binarySearch(int value) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == value) return mid;
            if (data[mid] < value) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public void bubbleSort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j] < data[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) swap(i, minIdx);
        }
    }

    public void insertionSort() {
        for (int i = 1; i < size; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public void clear() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] toArray() {
        int[] result = new int[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = data.length + (data.length >> 1) + 1;
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice fuera de rango");
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
