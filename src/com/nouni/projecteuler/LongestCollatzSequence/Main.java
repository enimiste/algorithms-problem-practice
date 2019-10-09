package com.nouni.projecteuler.longestCollatzSequence;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		R r = longestCollatzSequence((int)Math.pow(10, 6));
		System.out.println(r);
		
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	static R longestCollatzSequence(int n) {
		Map<Long, Integer> lengths = new HashMap<>(n);//cache to hold previous sequences length
		Long i = 2L;
		Long x;
		int c;
		boolean ignore;
		
		while(i <= n) {
			x = i;
			c = 0;
			ignore = false;
			while(x != 1) {
				x = collatzTerm(x);
				c++;
				//if we already calculating sequence that starts from x, we avoid extras calcul
				if(lengths.containsKey(x)) {
					lengths.put(i, lengths.get(x) + c);
					ignore = true;
					break;
				}
			}
			if(!ignore) {
				lengths.put(i, c + 1);
			}
			i++;
		}
		System.out.println(lengths.size());
		return lengths.entrySet().stream().max((e1, e2) -> e1.getValue() - e2.getValue()).map(e -> R.as(e.getKey(), e.getValue())).get();
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	static Long collatzTerm(Long n) {
		return (n % 2 == 0) ? n/2 : (3*n+1);
	}
	
	/**
	 * 
	 * @author e.nouni
	 *
	 */
	static class R {
		public long n;//starting number of the largest collatz sequence
		public long l;//Sequence length
		
		static R as(long n, long l) {
			R r = new R();
			r.n = n;
			r.l = l;
			return r;
		}
		
		@Override
		public String toString() {
			return String.format("R(n=%d, l=%d)", n, l);
		}
	}

}
