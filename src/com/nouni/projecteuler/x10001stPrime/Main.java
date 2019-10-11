package com.nouni.projecteuler.x10001stPrime;

import java.util.*;

public class Main {
    public static void main(String... args) {
        PrimeNumberFounder pnf = new SieveOfEratosthenes();
        //10001 in 198.039sec
        PrimeNumberFounder.R p = pnf.nth(10001);
        System.out.println("-".repeat( 30));
        System.out.println(p);
    }

    public static void showArr(int[] arr) {
        System.out.print("[");
        for(int x : arr) {
            System.out.print(x + ",");
        }
        System.out.println("]");
    }
}

interface PrimeNumberFounder {
    /**
     * Ex : By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
     * @return  the nth prime number
     */
    R nth(int nth);

    static class R {
        protected int total;
        protected int n;
        protected Optional<Integer> nth;
        protected long last;
        protected int maxInt;

        static R as(int t, int n, Optional<Integer> nth, long l, int max) {
            R r = new R();
            r.total = t;
            r.n = n;
            r.nth = nth;
            r.last = l;
            r.maxInt = max;
            return r;
        }

        @Override
        public String toString() {
            return String.format("R(maxInt=%d, total=%d, %dth=%d, last=%d)", maxInt, total, n, nth.orElse(-1), last);
        }
    }
}

class SieveOfEratosthenes implements PrimeNumberFounder {

    @Override
    public R nth(int nth) {
        final int MAX_INT = (int)(2 * nth * Math.log(nth));
       
        int i = 0;
        int[] numbers = range(2, MAX_INT);
        LinkedList<Integer> primes = makeFrom(numbers);
        do {
            if(nth == i + 1) break;
            removeMultiples(primes, i);
            i++;
        } while(i < primes.size());
        //primes.stream().limit(10).forEach(System.out::println);
        R r = null;
        if(primes.size() < nth) {
            r = R.as(primes.size(), nth, Optional.empty(), primes.getLast(), MAX_INT);
        } else {
            r = R.as(primes.size(), nth, Optional.of(primes.get(nth - 1)), primes.getLast(), MAX_INT);
        }
        return r;
    }

    static void removeMultiples(LinkedList<Integer> primes, int from) {
        Integer e = primes.get(from);
        int j = primes.indexOf(e * e);
        if(j < 0) return;

        while(j < primes.size()) {
            Integer x = primes.get(j);
            if(x % e == 0) {//% is costly
                primes.remove(j);
            } else 
                j++;
            
        }
    }

    static LinkedList<Integer> makeFrom(int[] items) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        for(int x : items) {
            res.add(x);
        }
        return res;
    }

    static int[] range(int from, int to) {
        int[] res = new int[to - from + 1];
        int i = 0;
        while(to >= from) {
            res[i++] = from++;
        }

        return res;
    }
}