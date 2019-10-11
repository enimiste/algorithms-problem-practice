package com.nouni.projecteuler.primesSummation;

import java.util.*;

public class Main {
	public static void main(String... args) {
		int max = 2 * (int) Math.pow(10, 5);
		Integer[] primes = SieveOfEratosthenes.primes(max);
		/*
		 * for(Integer x : primes) { System.out.println(x); }
		 */
		System.out.println(primes.length + " primes found");
		System.out.println("-".repeat(20));
		long s = sum(primes);
		System.out.println("Sum of all primes below " + max + " is " + s);
	}

	static long sum(Integer[] xs) {
		long s = 0L;
		for (int x : xs) {
			s += x;
		}
		return s;
	}
}

class SieveOfEratosthenes {
	// Take 5x excution time than the other one below
	static Integer[] primes_(int max) {
		LinkedList<Integer> primes = new LinkedList<>();
		primes.add(2);
		for (int i = 3; i <= max; i += 2) {
			primes.add(i);
		}

		for (int j = 0; j < primes.size(); j++) {
			Integer x = primes.get(j);
			int i = primes.indexOf(x * x);
			if (i < 0)
				continue;
			for (; i < primes.size(); i++) {
				if (primes.get(i) % x == 0) {
					primes.remove(i);
					i--;
				}
			}
		}

		return primes.toArray(new Integer[] {});
	}

	static Integer[] primes(int max) {
		Integer[] res = new Integer[max];
		int i = 0;
		int p = 3;
		boolean div = false;

		res[i++] = 2;// first prime
		while (p <= max) {
			div = false;
			for (int j = 0; j < i; j++) {
				if (p % res[j] == 0) {
					div = true;
					break;
				}
			}
			if (!div)
				res[i++] = p;
			p += 2;
		}

		return Arrays.copyOfRange(res, 0, i);
	}
}
