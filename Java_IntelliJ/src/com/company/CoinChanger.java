package com.company;

import java.io.IOException;
import java.util.Arrays;

public class CoinChanger {
    int[][] mTotalWays;
    int[] mCoins;
    int mTotal;
    final int asUnknownWays = -1;

    CoinChanger(int[] coins, int total) {
        mCoins = coins; mTotal = total;
        setupTable();
    }

    private void setTotalWays(int forCoinSet,int forAmount,int asWays) {
        mTotalWays[forCoinSet][forAmount] = asWays;
    }
    private int totalWays(int forCoinSet,int forAmount) {
        return mTotalWays[forCoinSet][forAmount];
    }

    private void setupTable() {
        // set up a totalWay table where:
        // column(amount)                              0             1           2         ...        total
        // row(coinSet) {}                             .             .           .                    .
        //              {coins[0]}                     .             .           .                    .
        //              {coins[0],coins[1]}            .             .           .                    .
        //              ...
        //              {coins[0],coins[1]..coins[T]}  .             .           .                    FinalAnswer
        //
        // Each cell contains:
        //
        //           The total number of ways to make change for amount given in the column, using the
        //           set of coins in the row.
        //
        // Each row represents the set of coins, where row 5, for example represents the set of coins:
        // {coins[0],coins[1],coins[2],coins[3],coins[4]}
        // So this means the set of coins with each denomination up to and including the 5th coin's denomination.
        mTotalWays = new int[mCoins.length+1][mTotal+1];

        // Fill entire table with unknown ways
        for ( int forCoinSet = 0; forCoinSet <= mCoins.length; forCoinSet++ )
            for ( int forAmount = 0; forAmount <= mTotal; forAmount++ )
                setTotalWays(forCoinSet,forAmount,asUnknownWays);

        // Base Cases
        // ----------
        // If amount == 0 then just return empty set to make the change
        final int emptySetCountsAsOneWay = 1;
        final int forZeroAmount = 0;
        for (int forCoinSet = 0; forCoinSet <= mCoins.length; forCoinSet++) {
            setTotalWays(forCoinSet, forZeroAmount, emptySetCountsAsOneWay);
        }
        // if coinSet == 0 then there are 0 ways to make change
        final int asZeroWays = 0;
        final int forZeroCoinSet = 0;
        for ( int forAmount = 1; forAmount <= mTotal; forAmount++ ) {
            setTotalWays(forZeroCoinSet, forAmount, asZeroWays);
        }
    }

    public int waysToMakeChange(boolean trace) {

        //        0     1       2       3           4           5
        // 0      1     0       0       0           0           0
        //        {}
        // 1      1     1       1       1           1           1
        //        {}    {1}     {1,1}   {1,1,1}     {1,1,1,1}   {1,1,1,1,1}
        // 1,2    1     1       2       2           3           3
        //        {}    {1}     {1,1},  {1,1,1}     {1,1,1,1}   {1,1,1,1,1}
        //                      {}+{2}  {1}+{2}     {1,1}+{2}   {1,1,1}+{2}
        //                                          {}+{2}+{2}  {1}+{2}+{2}
        // 1,2,3  1 1 2 3 4 5

        // start with the first coin in the first row, then increase to larger and larger coins sets,
        // until reaching the set of all coins
        for ( int forCoinSet = 1; forCoinSet <= mCoins.length; forCoinSet++ ) {
            int coinValue = mCoins[forCoinSet-1];
            for ( int forAmount = 1; forAmount <= mTotal; forAmount++ ) {
                boolean amountCoversCoinValue = forAmount >= coinValue;
                int newWays = 0;
                if (amountCoversCoinValue) {
                    newWays = totalWays(forCoinSet, forAmount - coinValue);
                }
                int waysWithPreviousCoinSet = totalWays(forCoinSet - 1, forAmount);
                setTotalWays(forCoinSet, forAmount, waysWithPreviousCoinSet + newWays);

                if ( trace ) {
                    printTotalWaysTable();
                    System.out.println();
                    System.out.println("Press ENTER to continue...");
                    try { waitForEnterKey(); } catch (IOException exception) {
                        System.out.println(""+ Arrays.toString(exception.getStackTrace()));
                    }
                }
            }
        }
        return totalWays(mCoins.length, mTotal);
    }

    private static void printStringWithWidth(String string, int width) {
        int margin = width - string.length();
        System.out.print(""+string);
        for ( int i = 0; i < margin; i++ ) {
            System.out.print(" ");
        }
    }

    private String getCoinSetString(int coinSet) {
        if (coinSet==0) return "{}";
        StringBuilder stringBuilder = new StringBuilder("{");
        for ( int i = 1; i <= coinSet; i++ ) {
            String separator = i==coinSet?"":",";
            stringBuilder.append("").append(mCoins[i - 1]).append(separator);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void printTotalWaysTable() {
        clearScreen();
        final String headingTitle = "row total ";
        final int coinSetMaxWidth = getCoinSetString(mCoins.length).length()+1;
        final int spacesPerHeading = Math.max(coinSetMaxWidth,headingTitle.length());
        printStringWithWidth(headingTitle, spacesPerHeading);
        final int spacesPerCell = 10;
        for ( int forAmount = 0; forAmount < mTotalWays[0].length; forAmount++ ) {
            printStringWithWidth(""+forAmount, spacesPerCell);
        }
        System.out.println();

        for ( int forCoinSet = 0; forCoinSet < mTotalWays.length; forCoinSet++ ) {
            printStringWithWidth(getCoinSetString(forCoinSet), spacesPerHeading);
            for ( int forAmount = 0; forAmount < mTotalWays[forCoinSet].length; forAmount++ ) {
                int totalWays = totalWays(forCoinSet,forAmount);
                String waysString = totalWays==asUnknownWays ? "" : ""+totalWays;
                printStringWithWidth(waysString, spacesPerCell);
            }
            System.out.println();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int waitForEnterKey() throws IOException {
        return System.in.read();
    }
}
