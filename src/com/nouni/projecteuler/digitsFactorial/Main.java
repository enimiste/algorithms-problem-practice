package com.nouni.projecteuler.digitsFactorial;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Main {
	static final int MAX = 362880;// 9!

	public static void main(String... args) {
		Map<Integer, Integer> baseDF = baseDigitsFactorials();
		System.out.println(equalsDigitsFactorial(Integer.MAX_VALUE, baseDF));
	}

	static int digtitsSumFactorial(int x, Map<Integer, Integer> baseDigitsFactorials) {
		if (x <= 9)
			return baseDigitsFactorials.get(x);
		int y = x % 10;
		x = x / 10;
		int sum = baseDigitsFactorials.get(y);
		while (x > 0) {
			y = x % 10;
			x = x / 10;
			sum += baseDigitsFactorials.get(y);
		}

		return sum;
	}

	static List<Integer> equalsDigitsFactorial(int max, Map<Integer, Integer> baseDigitsFactorials) {
		return Stream.iterate(3, x -> x + 1).limit(max).filter(x -> x == digtitsSumFactorial(x, baseDigitsFactorials))
				.collect(Collectors.toList());
	}

	static Map<Integer, Integer> baseDigitsFactorials() {
		return Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }).stream()
				.map(x -> new Integer[] { x, factorial(x) }).collect(Collectors.toMap(x -> x[0], y -> y[1]));

	}

	static int factorial(int x) {
		if (x <= 1)
			return 1;
		int sum = 1;
		while (x > 1) {
			sum *= x--;
		}
		return sum;
	}
}