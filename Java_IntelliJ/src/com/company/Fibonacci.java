package com.company;

public class Fibonacci {
    public static long fibonacci(int n) {
        return n > 1 ? fibonacci(n-1) + fibonacci(n-2) : n;
    }
    static final int LOOKUP_TABLE_SIZE = 50;
    static long sLookupTable[] = new long[LOOKUP_TABLE_SIZE];
    public static long fastFibonacci(int n) {
        long answer = sLookupTable[n];
        if ( answer == 0 ) {
            answer = n > 1 ? fastFibonacci(n-1) + fastFibonacci(n-2) : n;
            sLookupTable[n] = answer;
        }
        return answer;
    }
}
