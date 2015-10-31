package com.company;

public class Main {

    public static void main(String[] args) {
        CoinChanger changer = new CoinChanger(new int[]{1,5,10},13);
        changer.waysToMakeChange(true);
        changer.printTotalWaysTable();
    }
}
