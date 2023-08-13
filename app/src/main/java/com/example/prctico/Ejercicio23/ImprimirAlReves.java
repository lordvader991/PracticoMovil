package com.example.prctico.Ejercicio23;

import java.util.concurrent.Callable;

public class ImprimirAlReves implements Callable<String> {

    private String inputStr;

    public ImprimirAlReves(String inputStr) {
        this.inputStr = inputStr;
    }

    @Override
    public String call() throws Exception {
        return reverseString(inputStr);
    }

    private String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            char lastChar = str.charAt(str.length() - 1);
            String remainingString = str.substring(0, str.length() - 1);
            return lastChar + reverseString(remainingString);
        }
    }
}




