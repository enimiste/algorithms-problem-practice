package com.nouni.projecteuler.sumSquareDifference;

import java.util.*;

public class Main {
	public static void main(String... args) {
		int n = 100;
		long sq = sumSquares(n);
		long qs = squareSum(n);
		System.out.println(sq);
		System.out.println(qs);
		System.out.println(qs - sq);
	}

	static long squareSum(int n) {
		long x = sum(n);
		return x * x;
	}

	static long sumSquares(int n) {
		return Math.round(n * n * n / 3.0 + n * n / 2.0 + n / 6.0);
	}

	static long sum(int n) {
		return n * (n + 1) / 2;
	}
}
