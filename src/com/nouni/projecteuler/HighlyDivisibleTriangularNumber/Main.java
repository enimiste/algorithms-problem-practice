package com.nouni.projecteuler.highlyDivisibleTriangularNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
	static final Integer MAX_TERMS = Integer.MAX_VALUE;//To avoid infinite stream
	
	public static void main(String[] args) {
		final int divs = 500;
		findTriangular(divs).ifPresent(System.out::println);
		System.out.println("End");
	}

	/**
	 * 
	 * @param divsCount
	 * @return
	 */
	private static Optional<R> findTriangular(int divsCount) {
		DivisorsFinder df = new MyDivisorsFinder();
		return terms()
				.limit(MAX_TERMS)
				.map(t -> t.factories(df))
				.filter(t -> t.hasOverDivsSize(divsCount + 1))
				.findFirst()
				.map(t -> R.as(t.nth, t.n, t.divs));
	}

	static Stream<Term> terms() {
		return Stream.iterate(Term.initial(), (x) -> x.next());
	}

 	static class R {
		protected long nth;//rang
		protected long n;
		protected int[] divs;

		private R() {

		}

		static R as(long nth, long n, int[] ds) {
			R r = new R();
			r.n = n;
			r.nth = nth;
			r.divs = ds;
			Arrays.sort(r.divs);
			return r;
		}

		int divsSize() {
			return divs == null ? 0 : (divs.length - 1);
		}
		
		String formatDivs() {
			StringBuilder sb = new StringBuilder();
			for(int i : divs) {
				sb.append(i).append(", ");
			}
			return sb.toString();
		}

		@Override
		public String toString() {
			return String.format("R(nth=%d, n=%d, dsize=%d) : %s", nth, n, divsSize(), formatDivs());
		}
	}

	static class Term {
		int nth;//rang
		int[] divs;//divisors
		long n;// Xn
		protected long x;// Xn-1
		protected long y;// Xn-2

		private Term() {

		}

		static Term initial() {
			Term t = new Term();
			t.nth = 1;
			t.n = 1;
			t.x = 3;
			t.y = 1;
			return t;
		}

		static Term as(int nth, long n, long x, long y) {
			Term t = new Term();
			t.nth = nth;
			t.n = n;
			t.x = x;
			t.y = y;
			return t;
		}

		long value() {
			return n;
		}

		Term next() {
			if (n == 1) {
				n = 3;
			} else if (n == 3) {
				n = 6;
			} else {
				long tmp = n;
				n = 2 * n - x + 1;
				y = x;
				x = tmp;
			}
			nth++;
			return this;

		}
		
		int getDivsSize() {
			return divs == null ? 0 : divs.length;
		}
		
		boolean hasOverDivsSize(int size) {
			return getDivsSize() >= size;
		}
		
		Term factories(DivisorsFinder df) {
			divs = df.find(n);
			return this;
		}

		@Override
		public String toString() {
			return String.format("T(nth=%d, n=%d, n-1=%d, n-2=%d)", nth, n, x, y);
		}
	}
	
	static interface DivisorsFinder {
		int[] find(long n);
	}
	
	static class MyDivisorsFinder implements DivisorsFinder {

		@Override
		public int[] find(long n) {
			int sn = (int)Math.sqrt(n), j = 0;
			Set<Integer> set = new HashSet<>(sn * 2);
			for(int i = 1; i <= sn; i++) {
				if(n % i == 0) {
					set.add(i);
					set.add((int) (n / i));
				}
			}
			int[] divs = new int[set.size()];
			for(Integer x : set) {
				divs[j++] = x;
			}
			
			return divs;
		}
	}
}
