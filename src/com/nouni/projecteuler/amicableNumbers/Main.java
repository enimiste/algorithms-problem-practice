package com.nouni.projecteuler.amicableNumbers;

import java.util.*;
import java.util.stream.*;


public class Main {
    public static void main(String... args) {
        int max = 10000;
        
        System.out.println(amicalNumbersSum(max));
    }

    static int amicalNumbersSum(int max) {
        Map<Integer, Integer> r = amicalNumbers(max);

        return r.entrySet().stream().mapToInt((e) -> e.getValue() + e.getKey()).reduce(0, Integer::sum);
    }

    static Map<Integer, Integer> amicalNumbers(int max) {
        Map<Integer, Integer> s = divisorsSum(max);
        Map<Integer, Integer> r = new HashMap<>();
        for(Integer k1 : s.keySet()) {
            Integer v1 = s.get(k1);
            if(k1.intValue() != v1.intValue() && s.containsKey(v1)) {
                Integer v2 = s.get(v1);
                if(k1.intValue() == v2.intValue()) {
                    r.put(Math.min(k1, v1), Math.max(k1, v1));
                    
                }
            }
        }
        return r;
    }

    static Map<Integer, Integer> divisorsSum(int max) {
        return Stream.iterate(2, (x) -> x <= max, (x) -> x + 1)
            .map(x -> new Integer[]{x, sum(divisors(x))})
            .filter(e -> e[1] <= max && e[1] > 1)
            .collect(Collectors.toMap((x) -> x[0], (x) -> x[1]));
    }

    static int sum(int... xs) {
        int s = 0;
        for(int x : xs) {
            s += x;
        }
        return s;
    }


    static int[] divisors(int n) {
        int size = n / 2;
        int[] ds = new int[size + 1];
        int i = 0;
        ds[i++] = 1;
        for(int j = 2; j <= size; j++) {
            if(n % j == 0) ds[i++] = j;
        }
        return Arrays.copyOfRange(ds, 0, i);
    }

    static void show(int[] xs) {
        System.out.print("[");
        for(int x : xs) {
            System.out.print(x + ", ");
        }
        System.out.println("]");
    }
}