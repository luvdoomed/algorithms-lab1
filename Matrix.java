package hse.algorithms;
public class Matrix {

    private final int[][] matrix;
    private final int rows;
    private final int columns;
    private final int key;

    Matrix(int rows, int columns, int key) {
        this.rows = rows;
        this.columns = columns;
        this.key = key;
        this.matrix = new int[rows][columns];
    }

    public void generateInTheFirstWay() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] = (columns / rows * i + j) * 2;
            }
        }
    }

    public void generateInTheSecondWay() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] = (columns / rows * (i + 1) * (j + 1)) * 2;
            }
        }
    }

    public boolean ladderSearch() {
        int row = 0, column = columns - 1;
        while (row < rows && column > -1) {
            if (matrix[row][column] == key) {
                return true;
            } else if (matrix[row][column] < key) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }

    public boolean ladderExpSearch() {
        int row = 0, column = columns - 1;
        while (row < rows) {
            if (matrix[row][column] == key) {
                return true;
            } else if (matrix[row][column] > key) {
                int start = 1;
                while ((column - start > -1) && (matrix[row][column - start] > key)) {
                    start = start * 2;
                }
                int finish = column - start / 2;
                start = Math.max(column - start, 0);
                if (start == finish) {
                    return false;
                }
                column = binSearch(row, start, finish);
                if (matrix[row][column] == key) {
                    return true;
                }
            } else {
                row++;
            }
        }
        return false;
    }


    public boolean binarySearch() {
        for (int i = 0; i < rows; ++i) {
            int result = binSearch(i, 0, columns - 1);
            if (matrix[i][result] == key) {
                return true;
            }
        }
        return false;
    }

    private int binSearch(int row, int start, int finish) {
        while (start <= finish) {
            int middle = (start + finish) / 2;
            if (matrix[row][middle] == key) {
                return middle;
            }
            if (matrix[row][middle] > key) {
                finish = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return finish;
    }
}
