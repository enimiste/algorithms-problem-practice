package com.nouni.projecteuler.exhaustifPathMaxSum;

import java.util.*;
import java.util.stream.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("- Start");
		int[][] triangle = new int[][] { { 8 }, { -49, 13 }, { 28, -44, 9 }, { 40, -27, -46, 22 },
				{ 0, -31, -33, -18, 0 }, };

		Arrays.stream(triangle).map(Arrays::toString).forEach(System.out::println);
		int[] maxSums = pathsMaxSums(triangle);
		System.out.println("- Max sums at N-1 :");
		System.out.println(Arrays.toString(maxSums));
		System.out.println("- Max path sum :");
		IntStream.of(maxSums).max().ifPresent(System.out::println);
	}

	/**
	 * 
	 * @param triangle
	 * @return
	 */
	private static int[] pathsMaxSums(int[][] triangle) {
		if (triangle == null || triangle.length == 0)
			return new int[] {};
		else if (triangle.length == 1)
			return new int[] { triangle[0][0] };
		else if (triangle.length == 2)
			return new int[] { triangle[0][0] + triangle[1][0], triangle[0][0] + triangle[1][1] };
		else {
			int n = triangle.length;
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = dp(triangle, n - 1, i, n);
			}
			return res;
		}
	}

	/**
	 * 
	 * @param r row
	 * @param c column
	 * @return
	 */
	private static int dp(int[][] values, int r, int c, int n) {
		int current = values[r][c];
		if (r > 0) {
			int[] sums = new int[n];
			for (int i = 0; i < n - 1; i++) {// iterate of all columns of the row r-1
				sums[i] = dp(values, r - 1, i, n - 1);
			}
			current += IntStream.of(sums).max().getAsInt();
		}
		return current;
	}

}
