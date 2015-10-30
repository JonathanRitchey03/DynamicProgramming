package com.company;

public class CoinChange {
    public static int waysToMakeChangeFromCoinDenomationsAndTotal(int[] denominations, int total) {
        int differentCoins = denominations.length;
        int[] totalWays[] = new int[total+1][differentCoins];

        //        0 1 2 3 4 5
        // 1      1 1 1 1 1 1
        // 1,2    1 1 2 2 3 3
        // 1,2,3  1 1 2 3 4 5

        for ( int coinIndex = 0; coinIndex < differentCoins; coinIndex++ ) {
            int coinValue = denominations[coinIndex];
            for ( int t = 0; t <= total; t++ ) {
                if ( t == 0 || coinIndex == 0 ) {
                    totalWays[t][coinIndex] = 1;
                } else {
                    int waysWithPreviousCoins = totalWays[t][coinIndex-1];
                    int newWays = 0;
                    boolean isTotalMinusCoinValueOutOfBounds = t - coinValue < 0;
                    if ( !isTotalMinusCoinValueOutOfBounds ) {
                        newWays = totalWays[t - coinValue][coinIndex];
                    }
                    totalWays[t][coinIndex] = waysWithPreviousCoins + newWays;
                }
            }
        }
        System.out.print(" ");
        for ( int i = 0; i <= total; i++ ) {
            System.out.print(" " + i);
        }
        System.out.println();
        for ( int coinIndex = 0; coinIndex < differentCoins; coinIndex++ ) {
            System.out.print(""+denominations[coinIndex]+" ");
            for ( int t = 0; t <= total; t++ ) {
                System.out.print(""+totalWays[t][coinIndex]+" ");
            }
            System.out.println();
        }
        return 0;
    }
}
