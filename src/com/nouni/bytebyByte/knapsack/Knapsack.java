package com.nouni.bytebyByte.knapsack;

import java.util.*;
import java.util.stream.*;

public class Knapsack {

	public static void main(String[] args) {
		System.out.println("Start");
		int maxWeight = 5;
		int[][] knaps = {
				{1, 6},//{w,v}
				{2, 10},
				{3, 12}
		};
		
		Arrays.stream(knaps).map(Arrays::toString).forEach(System.out::println);
		List<Knap> values = dp(Knap.as(knaps), new ArrayList<Knap>(), -1, 0, maxWeight);
		values.forEach(System.out::println);
	}

	private static List<Knap> dp(LinkedList<Knap> knaps, List<Knap> sum, int i, int j, int maxWeight) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Knap {
		public int w;
		public int v;
		
		static Knap as(int w, int v) {
			Knap k = new Knap();
			k.w = w;
			k.v = v;
			return k;
		}
		
		static Knap as(int[] arr) {
			Knap k = new Knap();
			k.w = arr[0];
			k.v = arr[1];
			return k;
		}

		static LinkedList<Knap> as(int[][] knaps) {
			LinkedList<Knap> res = new LinkedList<>();
			Arrays.stream(knaps).map(Knap::as).forEach(res::add);
			return res;
		}
	}
}
