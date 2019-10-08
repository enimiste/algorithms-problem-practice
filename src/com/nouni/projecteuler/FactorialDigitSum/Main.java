package com.nouni.projecteuler.FactorialDigitSum;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		double st = System.nanoTime();
		run(args);
		double et = System.nanoTime();
		double d = et - st;
		d /= 1000;
		d /= 1000;
		d /= 1000;
		System.out.println("Execution time in sec : " + d);
	}
	
	static void run(String... args) throws Exception {//for 100! in 0.0212 sec
		System.out.println(digitsSumFactorial(100));
	}
	
	static int digitsSumFactorial(int n) {
		BigInteger x = factorial(n);
		return Arrays.asList(x.toString().split("")).stream().mapToInt(Integer::valueOf).reduce(0, Integer::sum);
	}

	static BigInteger factorial(int n) {//100! in 0.0020 sec
		BigInteger bi = BigInteger.ONE;
		for(int i = 2; i <= n; i++) {
			bi = bi.multiply(BigInteger.valueOf(i));
		}
		return bi;
	}
}
