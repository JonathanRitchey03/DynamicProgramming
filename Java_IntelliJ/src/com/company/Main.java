package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("f(40) " + Fibonacci.fibonacci(40));

        CoinChanger changer = new CoinChanger(new int[]{1,2,3},5);
        changer.waysToMakeChange(true);
    }
}
