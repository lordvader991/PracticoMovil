package com.example.prctico.Ejercicio17;

import java.util.concurrent.Callable;

public class MayorFinder implements Callable<Integer> {

    private int[] numbers;

    public MayorFinder(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return findLargestNumber(numbers);
    }

    private int findLargestNumber(int[] arr) {
        return findLargestNumberHelper(arr, 0, arr.length - 1);
    }

    private int findLargestNumberHelper(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }

        int mid = (low + high) / 2;
        int leftMax = findLargestNumberHelper(arr, low, mid);
        int rightMax = findLargestNumberHelper(arr, mid + 1, high);

        return Math.max(leftMax, rightMax);
    }
}

