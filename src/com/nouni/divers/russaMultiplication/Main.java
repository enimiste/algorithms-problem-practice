package com.nouni.divers.russaMultiplication;

import java.math.BigInteger;

/*Wikipidia :
 * In mathematics, ancient Egyptian multiplication (also known as Egyptian multiplication, Ethiopian multiplication, Russian multiplication, or peasant multiplication), 
 * one of two multiplication methods used by scribes, was a systematic method for multiplying two numbers that does not require the multiplication table, 
 * only the ability to multiply and divide by 2, and to add. It decomposes one of the multiplicands (preferably the smaller) into a sum of powers of two and 
 * creates a table of doublings of the second multiplicand. This method may be called mediation and duplation, where mediation means halving one number and duplation
 *  means doubling the other number. It is still used in some areas. 
 */
public class Main {

	public static void main(String[] args) {
		long[][] tests = { 
				{ 17, 53, 901 }, 
				{ 53, 17, 901 }, 
				{ 233, 18, 4194 }, 
				{ 0, 10, 0 },
				{ 20, 0, 0 },
				{ 0, 0, 0 },
				{ 10, 10, 100 },
				{ 42, 1, 42 }, 
				{ 9999999, 10, 99999990 }, 
				{ -1, 10, -10 },
				{ 1, -10, -10 },
				{ -1, -10, 10 },
				{Integer.MAX_VALUE, Integer.MAX_VALUE, (long)Integer.MAX_VALUE * Integer.MAX_VALUE}
			};

		for (long[] test : tests) {
			int x = (int) test[0], y = (int) test[1];
			long v = russaMultip(x, y), expected = test[2];
			if (v == expected) {
				System.out.println(String.format("Correct\tcalculation %d + %d = %d", x, y, v));
			} else {
				System.out.println(String.format("Wrong\tcalculation %d + %d = %d (%d)", x, y, v, expected));
			}
		}
	}

	private static long russaMultip(int factor1, int factor2) {
		if (factor1 == 0 || factor2 == 0)
			return 0;
		int  right;
		long res = 0, left;
		int sign = 1;
		// Handle negatif numbers case
		if (factor1 < 0) {
			sign *= -1;
			factor1 = factor1 * -1;// abs
		}
		if (factor2 < 0) {
			sign *= -1;
			factor2 = factor2 * -1;// abs
		}
		// Performance : it is better to use the smaller number as the right factor
		if (factor1 < factor2) {
			left = factor2;
			right = factor1;
		} else {
			left = factor1;
			right = factor2;
		}
		do {
			// stop condition
			if (right == 1) {
				res += left;
				break;
			} else if (right % 2 != 0) {
				res += left;
			}
			left *= 2;
			right /= 2;
		} while (true);
		return res * sign;
	}

}
