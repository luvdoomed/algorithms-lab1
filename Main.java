package hse.algorithms;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int rows = 2, columns = 8192, numberOfTests = 10000;

        while (rows <= 8912) {
            checkMethods(rows, columns, numberOfTests, "First");
            checkMethods(rows, columns, numberOfTests, "Second");
            rows = rows * 2;
        }
    }

    private static void checkMethods(int rows, int columns, int numberOfTests, String generationWay) {
        ArrayList<Long> binaryTimes = new ArrayList<>();
        ArrayList<Long> ladderTimes = new ArrayList<>();
        ArrayList<Long> expTimes = new ArrayList<>();
        Matrix matrix;

        if (generationWay.equals("First")) {
            matrix = new Matrix(rows, columns, 2 * columns + 1);
            matrix.generateInTheFirstWay();
        } else {
            matrix = new Matrix(rows, columns, 16 * columns + 1);
            matrix.generateInTheSecondWay();
        }

        for (int i = 0; i < numberOfTests; ++i) {
            long start = System.nanoTime();
            matrix.binarySearch();
            binaryTimes.add(System.nanoTime() - start);

            start = System.nanoTime();
            matrix.ladderSearch();
            ladderTimes.add(System.nanoTime() - start);

            start = System.nanoTime();
            matrix.ladderExpSearch();
            expTimes.add(System.nanoTime() - start);
        }

        long sumBinaryTimes = 0, sumLadderTimes = 0, sumExpTimes = 0;
        for (int i = 0; i < numberOfTests; ++i) {
            sumBinaryTimes += binaryTimes.get(i);
            sumLadderTimes += ladderTimes.get(i);
            sumExpTimes += expTimes.get(i);
        }

        if (generationWay.equals("First")) {
            System.out.println("The first type of generation for matrix " + rows + "x" + columns + ":");
            printTimes(sumBinaryTimes, sumLadderTimes, sumExpTimes, binaryTimes, ladderTimes, expTimes);
        } else {
            System.out.println("The second type of generation for matrix " + rows + "x" + columns + ":");
            printTimes(sumBinaryTimes, sumLadderTimes, sumExpTimes, binaryTimes, ladderTimes, expTimes);
        }
    }

    private static void printTimes(long sumBinaryTimes, long sumLadderTimes, long sumExpTimes,
                                   ArrayList<Long> binaryTimes, ArrayList<Long> ladderTimes, ArrayList<Long> expTimes) {
        System.out.println("Average running time of the binary method: " + sumBinaryTimes / binaryTimes.size());
        System.out.println("Average running time of the ladder method: " + sumLadderTimes / ladderTimes.size());
        System.out.println("Average running time of the exponential method: " + sumExpTimes / expTimes.size());
        System.out.println("\n");
    }
}
