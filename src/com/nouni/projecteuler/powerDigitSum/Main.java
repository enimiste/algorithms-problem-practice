package com.nouni.projecteuler.powerDigitSum;

import java.math.BigInteger;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String... args) {
        long sum = digitsSum2();
        System.out.println(sum);
    }

    static long digitsSum2() {//1366 in 1.487 sec
        int p = 1000;
        int digitsCount = (int)(Math.log10(2) * p) + 1;
        int[] digits = new int[digitsCount];
        digits[digitsCount - 1] = 2;
        int r = 0, i = digitsCount - 1, x;
        for(int j = p - 1; j > 0; j--) {
            for(int k = digitsCount - 1; k >= i; k--) {
                x = 2 * digits[k] + r;
                r = x / 10;
                digits[k] = x % 10;
            }
            if(r != 0) {
                i--;
                digits[i] = r;
                r = 0;
            }
        }
        format(digits);
        long sum = 0L;
        for(int j = 0; j < digitsCount; j++) {
            sum += digits[j];
        }
        return sum;
    }

    static long digitsSum() {//1366 in 1.335 sec
        BigInteger bi = power((int)Math.pow(10, 3));
        String[] digits = bi.toString().split("");
        long sum = 0L;
        for(String d : digits) {
            sum += Integer.valueOf(d);
        }
        System.out.println(bi);
        return sum;
    }

    static void format(int[] xs) {
        System.out.print("[");
        for(int x : xs) {
            System.out.print(x + ",");
        }
        System.out.println("]");
    }

    static BigInteger power(int p) {
        return BigInteger.TWO.pow(p);
    }
}