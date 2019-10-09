package com.nouni.projecteuler.amicableNumbers;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		int max = 400;
		int sum = sumOfAllAmicalNums(max);
		System.out.println(sum);
		/*st.forEach((e) -> {
			System.out.println("> " + e.getKey());
			e.getValue().forEach(x -> System.out.println(" - " + x.getKey()));
		});*/
	}
	
	static int sumOfAllAmicalNums(int max) {
		Stream<Entry<Integer, List<Entry<Integer, Integer>>>> st = amicalNumbersGroups(max);
		return st.sorted((e1, e2) -> e1.getKey() - e2.getKey())
				.mapToInt(e -> e.getValue()
						.stream()
						.map(x -> x.getKey())
						.reduce(0, Integer::sum))
				.reduce(0, Integer::sum);
	}

	static Stream<Entry<Integer, List<Entry<Integer, Integer>>>> amicalNumbersGroups(int max) {
		return divisorsSum(max)
				.entrySet()
				.stream()
				.collect(Collectors.groupingBy((e) -> e.getValue()))
				.entrySet()
				.stream();
				//.filter(e -> e.getValue().size() >= 2 && e.getKey() != 1);*/
	}

	static Map<Integer, Integer> divisorsSum(int max) {
		Map<Integer, Integer> res = new HashMap<>(max);
		while(max > 1) {
			res.put(max, sum(divisors(max)));
			max--;
		}
		return res;
	}
	
	static int sum(int... xs) {
		int sum = 0;
		for(int x : xs) {
			sum += x;
		}
		return sum;
	}
	
	//all divisors except n it self
	static int[] divisors(int n) {
		int sqn = n / 2;
		int[] ds = new int[sqn];
		int i = 0;
		ds[i++] = 1;
		for(int j = 2; j <= sqn; j++) {
			if(n % j == 0) ds[i++] = j;
		}
		return Arrays.copyOfRange(ds, 0, i);
	}
	
	static void show(int[] xs) {
		System.out.print("[");
		for(int x : xs) {
			System.out.print(x + ", ");
		}
		System.out.print("]");
	}

}
