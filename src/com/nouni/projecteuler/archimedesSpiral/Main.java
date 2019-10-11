package com.nouni.projecteuler.archimedesSpiral;

public class Main {

	public static void main(String[] args) {
		long p = nextPrime(13);
		System.out.println(p);
	}

	/**
	 * Returns the next prime number
	 * 
	 * @param pn
	 * @return
	 */
	static long nextPrime(long pn) {
		long k = Math.max((long) Math.ceil((pn + 1) / 4.0), 2);
		if (8 * k - pn > pn + 2)
			k = pn + 2;
		System.out.println("k = " + k);
		while (!isPrime(8 * k - pn)) {
			k += 1;// Only odd numbers
			if (k > 100)
				break;
		}
		return 8 * k - pn;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	static boolean isPrime(long p) {
		System.out.println(p);
		return false;
	}

}
