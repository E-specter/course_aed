package aed.modules.arrays_3d.model;

import java.util.Random;

public class Array3D {
    private int[][][] data;
    private int depth;
    private int rows;
    private int cols;

    public Array3D() {
        this(3, 4, 4);
    }

    public Array3D(int depth, int rows, int cols) {
        if (depth <= 0 || rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("Dimensiones deben ser > 0");
        this.depth = depth;
        this.rows = rows;
        this.cols = cols;
        this.data = new int[depth][rows][cols];
    }

    public void set(int d, int r, int c, int value) {
        checkBounds(d, r, c);
        data[d][r][c] = value;
    }

    public int get(int d, int r, int c) {
        checkBounds(d, r, c);
        return data[d][r][c];
    }

    public void fill(int value) {
        for (int d = 0; d < depth; d++)
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    data[d][r][c] = value;
    }

    public void fillRandom() {
        Random rand = new Random();
        for (int d = 0; d < depth; d++)
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    data[d][r][c] = rand.nextInt(100);
    }

    public int[][] getSlice(int d) {
        if (d < 0 || d >= depth) throw new IndexOutOfBoundsException("Capa fuera de rango");
        int[][] slice = new int[rows][cols];
        for (int r = 0; r < rows; r++)
            System.arraycopy(data[d][r], 0, slice[r], 0, cols);
        return slice;
    }

    public int[] search(int value) {
        for (int d = 0; d < depth; d++)
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    if (data[d][r][c] == value) return new int[]{d, r, c};
        return null;
    }

    public int sum() {
        int total = 0;
        for (int d = 0; d < depth; d++)
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    total += data[d][r][c];
        return total;
    }

    public void clear() {
        fill(0);
    }

    public int depth() { return depth; }
    public int rows() { return rows; }
    public int cols() { return cols; }
    public int[][][] getData() { return data; }

    private void checkBounds(int d, int r, int c) {
        if (d < 0 || d >= depth || r < 0 || r >= rows || c < 0 || c >= cols)
            throw new IndexOutOfBoundsException(
                    "Índice fuera de rango: [" + d + "][" + r + "][" + c + "]");
    }
}
