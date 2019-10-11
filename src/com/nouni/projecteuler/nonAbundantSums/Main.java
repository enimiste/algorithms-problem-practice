package com.nouni.projecteuler.nonAbundantSums;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	final static int MAX = 28123;

	public static void main(String[] args) {
		long sum = numbersNotSumOfTwoAbondantNums(MAX).reduce(0, Integer::sum);
		System.out.println(sum);
	}

	static Stream<Integer> numbersNotSumOfTwoAbondantNums(int max) {
		List<Integer> abn = abondantNumbers(max).collect(Collectors.toList());
		return Stream.iterate(1, (x) -> x <= max, (x) -> x + 1).filter((x) -> !canBeWrittenAsSumOfTwoAbNum(x, abn));
	}

	static boolean canBeWrittenAsSumOfTwoAbNum(Integer n, List<Integer> abn) {
		boolean canBe = false;
		Iterator<Integer> abni = abn.iterator();
		Set<Integer> abns = new HashSet<>(abn);
		while (abni.hasNext()) {
			Integer x = abni.next();
			Integer y = n - x;
			if (abns.contains(y)) {
				canBe = true;
				break;
			}
		}
		return canBe;
	}

	static Stream<Integer> abondantNumbers(int max) {
		return Stream.iterate(1, (x) -> x <= max, (x) -> x + 1).filter((x) -> sumPproperDivisors(x) > x);
	}

	static int sumPproperDivisors(int n) {
		if (n == 1)
			return 0;

		int sum = 1, f = 2, s = 1;
		int max = (int) Math.sqrt(n);
		if (max * max == n)
			sum -= max;
		if (n % 2 != 0) {
			f = 3;
			s = 2;
		}

		while (f <= max) {
			if (n % f == 0) {
				sum += (f + n / f);
			}
			f += s;
		}
		return sum;
	}

	static int sum(int... xs) {
		int sum = 0;
		for (int x : xs) {
			sum += x;
		}
		return sum;
	}

	static void show(int[] xs) {
		System.out.print("[");
		for (int x : xs) {
			System.out.print(x + ", ");
		}
		System.out.print("]");
	}

}
