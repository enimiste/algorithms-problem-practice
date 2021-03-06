package com.nouni.bytebyByte.knapsack;

import java.util.Arrays;

public class Knapsack {

	public static void main(String[] args) {
		System.out.println("Start");
		int maxWeight = 5;
		int[][] knaps = { 
				{ 1, 6 }, // {w,v}
				{ 2, 10 }, 
				{ 3, 12 } 
		};

		Arrays.stream(knaps).map(Arrays::toString).forEach(System.out::println);
		int maxValue = dp(0, maxWeight, knaps);
		System.out.println("Max value : " + maxValue);
	}

	static int dp(int i, int maxWeight, int[][] knaps) {
		if(i >= knaps.length) return 0;
		if(maxWeight - knaps[i][0] < 0) return dp(i+1, maxWeight, knaps);
		return Math.max(dp(i+1, maxWeight - knaps[i][0], knaps) + knaps[i][1], dp(i+1, maxWeight, knaps));
	}
}
