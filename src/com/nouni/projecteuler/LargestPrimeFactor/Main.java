package com.nouni.projecteuler.LargestPrimeFactor;

import java.util.*;
import java.util.function.BiFunction;

public class Main {

	public static void main(String[] args) {
		double st = System.nanoTime();
		run();
		double et = System.nanoTime();
		double dt = (et - st)/1000;
		dt /= 1000;
		dt /= 1000;
		System.out.println("Excution time in secs: " + dt);
	}
	
	static void run(String... args) {
		long n = Long.MAX_VALUE;
		System.out.println(n);
		//largestPrimeFactor(n).ifPresent(System.out::println);
		//System.out.println(largestPrimeFactor2(n));
		//System.out.println(largestPrimeFactor3(n));
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	static Integer largestPrimeFactor2(long n) {
		Integer lf = 1, f = 3;
		BiFunction<Long, Integer, Long> remove = (x, p) -> {
			do {
				x = x / p;
			} while(x % p == 0);
			return x;
		};
		if(n % 2 == 0) {
			n = remove.apply(n, 2);
			lf = 2;
		}
		while(n > 1) {
			if(n % f == 0) {
				n = remove.apply(n, f);
				lf = f;
			}
			f += 2;
		}
		return lf;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	static Integer largestPrimeFactor3(long n) {
		Integer lf = 1, f = 3;
		long sqn;
		BiFunction<Long, Integer, Long> remove = (x, p) -> {
			do {
				x = x / p;
			} while(x % p == 0);
			return x;
		};
		if(n % 2 == 0) {
			n = remove.apply(n, 2);
			lf = 2;
		}
		sqn = (long)Math.sqrt(n);
		while(n > 1 && f <= sqn) {
			if(n % f == 0) {
				n = remove.apply(n, f);
				lf = f;
				sqn = (long)Math.sqrt(n);
			}
			f += 2;
		}
		return (int) ((n == 1) ? lf : n);
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	static Optional<Integer> largestPrimeFactor(long n) {
		Integer[] primes = primes((int)Math.sqrt(n));
		for(int i = primes.length - 1; i >= 0; i--) {
			if(n % primes[i]==0) {
				return Optional.of(primes[i]);
			}
		}
		return Optional.empty();
	}
	
	static Integer[] primes(int max) {
		Integer[] primes = new Integer[max];
		int i = 0, p = 3;
		primes[i++] = 2;
		boolean div;
		while(p <= max) {
			div = false;
			for(int j = 0; j < i; j++) {
				if(p % primes[j] == 0) {
					div = true;
					break;
				}
			}
			if(!div) primes[i++] = p;
			p+=2;
		}
		return Arrays.copyOfRange(primes, 0, i);
	}
	
	static String format(Object[] os) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Object o : os) {
			sb.append(o).append(",");
		}
		sb.append("]");
		return sb.toString();
	}

}
