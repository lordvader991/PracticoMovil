package com.example.prctico.ejercicio25;

import java.util.concurrent.Callable;

public class Sumas  implements Callable<Long> {
    private int number;
    public Sumas (int number){
        this.number = number;
    }

    @Override
    public Long call() throws Exception {
        return suma(number);
    }


    private long suma (int n){
        if (n == 0){
            return  0;
        } else{
            return  n % 10 + suma(n/10);
        }
    }
}
