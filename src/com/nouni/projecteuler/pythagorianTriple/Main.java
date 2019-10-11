package com.nouni.projecteuler.pythagorianTriple;

import java.util.*;

public class Main {
	public static void main(String... args) {
		R r = foundPytha(1000 * 1000);
		System.out.println(r);
		System.out.println(r.p());
	}

	static R foundPytha(int sumABC) {
		for (int a = 1; a <= sumABC; a++) {
			for (int b = a + 1; b <= sumABC; b++) {
				int c = sumABC - a - b;
				if (R.check(a, b, c))
					return R.as(a, b, c);
			}
		}
		throw new RuntimeException("Not found");
	}

	static class R {
		int a, b, c;

		static R as(int x, int y, int z) {
			if (y <= x || y >= z)
				throw new RuntimeException(String.format("a < b < c. a=%d, b=%d, c=%d given.", x, y, z));
			if (x * x + y * y != z * z)
				throw new RuntimeException("a^2 + b^2 = c^2");
			R r = new R();
			r.a = x;
			r.b = y;
			r.c = z;
			return r;
		}

		/**
		 * 
		 */
		static boolean check(int x, int y, int z) {
			return y > x && y < z && (x * x + y * y == z * z);
		}

		long p() {
			return a * b * c;
		}

		long s() {
			return a + b + c;
		}

		public String toString() {
			return String.format("[%d^2 + %d^2 = %d + %d = %d = %d^2]", a, b, a * a, b * b, c * c, c);
		}
	}
}
