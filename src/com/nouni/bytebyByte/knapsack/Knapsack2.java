package com.nouni.bytebyByte.knapsack;

import java.util.Arrays;

public class Knapsack2 {

	public static void main(String[] args) {
		System.out.println("Start");
		int maxWeight = 5;
		int[][] knaps = { 
				{ 1, 6 }, // {w,v}
				{ 2, 10 }, 
				{ 3, 12 } 
		};

		Arrays.stream(knaps).map(Arrays::toString).forEach(System.out::println);
		int maxValue = 0;
		System.out.println("Max value : " + maxValue);
	}
}
