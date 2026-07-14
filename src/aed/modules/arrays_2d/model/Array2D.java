package aed.modules.arrays_2d.model;

import java.util.Random;

public class Array2D {
    private int[][] data;
    private int rows;
    private int cols;

    public Array2D() {
        this(4, 4);
    }

    public Array2D(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("Dimensiones deben ser > 0");
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public void set(int row, int col, int value) {
        checkBounds(row, col);
        data[row][col] = value;
    }

    public int get(int row, int col) {
        checkBounds(row, col);
        return data[row][col];
    }

    public void fill(int value) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                data[r][c] = value;
            }
        }
    }

    public void fillRandom() {
        Random rand = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                data[r][c] = rand.nextInt(100);
            }
        }
    }

    public int[] getRow(int row) {
        if (row < 0 || row >= rows) throw new IndexOutOfBoundsException("Fila fuera de rango");
        int[] result = new int[cols];
        System.arraycopy(data[row], 0, result, 0, cols);
        return result;
    }

    public int[] getCol(int col) {
        if (col < 0 || col >= cols) throw new IndexOutOfBoundsException("Columna fuera de rango");
        int[] result = new int[rows];
        for (int r = 0; r < rows; r++) {
            result[r] = data[r][col];
        }
        return result;
    }

    public void transpose() {
        int[][] transposed = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                transposed[c][r] = data[r][c];
            }
        }
        data = transposed;
        int temp = rows;
        rows = cols;
        cols = temp;
    }

    public int[] search(int value) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (data[r][c] == value) return new int[]{r, c};
            }
        }
        return null;
    }

    public int sum() {
        int total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                total += data[r][c];
            }
        }
        return total;
    }

    public void clear() {
        fill(0);
    }

    public int rows() { return rows; }
    public int cols() { return cols; }
    public int[][] getData() { return data; }

    private void checkBounds(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols)
            throw new IndexOutOfBoundsException("Índice fuera de rango: [" + r + "][" + c + "]");
    }
}
