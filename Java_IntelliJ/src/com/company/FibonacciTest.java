package com.company;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FibonacciTest {
    @Test
    public void testFibonacci() throws Exception {
        assertEquals(Fibonacci.fibonacci(0),0);
        assertEquals(Fibonacci.fibonacci(1),1);
        assertEquals(Fibonacci.fibonacci(2),1);
        assertEquals(Fibonacci.fibonacci(3),2);
        assertEquals(Fibonacci.fibonacci(4),3);
        assertEquals(Fibonacci.fibonacci(5),5);
        assertEquals(Fibonacci.fibonacci(6),8);
        assertEquals(Fibonacci.fibonacci(7),13);
        assertEquals(Fibonacci.fibonacci(8),21);
        assertEquals(Fibonacci.fibonacci(9),34);
        assertEquals(Fibonacci.fibonacci(10),55);
    }
    @Test
    public void testFastFibonacci() throws Exception {
        assertEquals(Fibonacci.fastFibonacci(0),0);
        assertEquals(Fibonacci.fastFibonacci(1),1);
        assertEquals(Fibonacci.fastFibonacci(2),1);
        assertEquals(Fibonacci.fastFibonacci(3),2);
        assertEquals(Fibonacci.fastFibonacci(4),3);
        assertEquals(Fibonacci.fastFibonacci(5),5);
        assertEquals(Fibonacci.fastFibonacci(6),8);
        assertEquals(Fibonacci.fastFibonacci(7),13);
        assertEquals(Fibonacci.fastFibonacci(8),21);
        assertEquals(Fibonacci.fastFibonacci(9),34);
        assertEquals(Fibonacci.fastFibonacci(10),55);
    }
}