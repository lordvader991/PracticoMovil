package com.example.prctico.Ejercicio19;

import java.util.concurrent.Callable;

public class SumaNaturales implements Callable<Integer> {

    private int n;

    public SumaNaturales(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        return calculateSum(n);
    }

    private int calculateSum(int num) {
        if (num == 0) {
            return 0;
        }
        return num + calculateSum(num - 1);
    }
}
