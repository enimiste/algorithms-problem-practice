package com.nouni.projecteuler.smallestMultiple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		long sm = smallestMultiple(1, 20);
		System.out.println(sm);
	}

	static long smallestMultiple(int from, int to) {
		if (from > to)
			throw new RuntimeException("'from' should be less than or equal to 'to'");

		PowerDecomposition[] pds = new PowerDecomposition[to - from + 1];
		int j = 0;
		for (int i = from; i <= to; i++) {
			pds[j] = PowerDecomposition.ints(i);
			j++;
		}
		return PowerDecomposition.lcm(pds).value();
	}
}

class Monome implements PowerDecomposition {
	protected int p;// prime number
	protected int n;// exposant
	protected Long v = null;

	public Monome(int prime, int exp) {
		p = prime;
		n = exp;
	}

	public static Monome as(int p, int n) {
		return new Monome(p, n);
	}

	public static Monome as(int p) {
		return as(p, 1);
	}

	@Override
	public Polynome multiply(Monome o) {
		if (o.p == 0 || p == 0)
			return Polynome.as(as(0));
		else if (p == o.p)
			return Polynome.as(as(p, n + o.n));
		else
			return Polynome.as(this, o);
	}

	@Override
	public Polynome multiply(Polynome o) {
		return o.multiply(this);
	}

	@Override
	public String toString() {
		return String.format("%d = %d^%d", value(), p, n);
	}

	@Override
	public Long value() {
		if (v == null)
			v = (long) Math.pow(p, n);
		return v;
	}

}

interface PowerDecomposition {
	static Map<Integer, PowerDecomposition> ints = new HashMap<>();

	Polynome multiply(Monome o);

	Polynome multiply(Polynome o);

	/**
	 * 
	 * @param pds
	 * @return
	 */
	default Polynome multiply(PowerDecomposition... pds) {
		Polynome res = Polynome.as();
		for (PowerDecomposition p : pds) {
			res = res.multiply(p);
		}
		return res;
	}

	default Polynome multiply(PowerDecomposition o) {
		if (o instanceof Monome)
			return multiply((Monome) o);
		else if (o instanceof Polynome)
			return multiply((Polynome) o);
		throw new RuntimeException(o.getClass().getName() + " type not supported");
	}

	Long value();

	static PowerDecomposition ints(Integer n) {
		ints();// to load values
		if (ints.containsKey(n))
			return ints.get(n);
		throw new RuntimeException("No power decomposition found for " + n);
	}

	/**
	 * Less common multiplier
	 * 
	 * @param pds
	 * @return
	 */
	static PowerDecomposition lcm(PowerDecomposition... pds) {
		Map<Integer, Monome> res = new HashMap<>();
		for (PowerDecomposition p : pds) {
			if (p instanceof Monome) {
				Monome m = (Monome) p;
				res.compute(m.p, (k, v) -> {
					if (v == null)
						return m;
					if (m.n > v.n)
						return m;
					return v;
				});
			} else if (p instanceof Polynome) {
				for (Monome m : ((Polynome) p).monomes()) {
					res.compute(m.p, (k, v) -> {
						if (v == null)
							return m;
						if (m.n > v.n)
							return m;
						return v;
					});
				}
			}
		}
		return Monome.as(1).multiply(res.values().toArray(PowerDecomposition[]::new));
	}

	static PowerDecomposition[] ints() {
		if (ints.isEmpty()) {
			ints.put(Integer.valueOf(1), Monome.as(1));
			ints.put(Integer.valueOf(2), Monome.as(2));
			ints.put(Integer.valueOf(3), Monome.as(3));
			ints.put(Integer.valueOf(4), ints.get(2).multiply(ints.get(2)));
			ints.put(Integer.valueOf(5), Monome.as(5));
			ints.put(Integer.valueOf(6), ints.get(2).multiply(ints.get(3)));
			ints.put(Integer.valueOf(7), Monome.as(7));
			ints.put(Integer.valueOf(8), ints.get(2).multiply(ints.get(4)));
			ints.put(Integer.valueOf(9), ints.get(3).multiply(ints.get(3)));
			ints.put(Integer.valueOf(10), ints.get(2).multiply(ints.get(5)));
			ints.put(Integer.valueOf(11), Monome.as(11));
			ints.put(Integer.valueOf(12), ints.get(2).multiply(ints.get(6)));
			ints.put(Integer.valueOf(13), Monome.as(13));
			ints.put(Integer.valueOf(14), ints.get(2).multiply(ints.get(7)));
			ints.put(Integer.valueOf(15), ints.get(5).multiply(ints.get(3)));
			ints.put(Integer.valueOf(16), ints.get(2).multiply(ints.get(8)));
			ints.put(Integer.valueOf(17), Monome.as(17));
			ints.put(Integer.valueOf(18), ints.get(2).multiply(ints.get(9)));
			ints.put(Integer.valueOf(19), Monome.as(19));
			ints.put(Integer.valueOf(20), ints.get(2).multiply(ints.get(10)));
		}
		return ints.values().toArray(PowerDecomposition[]::new);
	}
}

class Polynome implements PowerDecomposition {
	protected Monome[] pm;
	protected Long v = null;

	public Polynome(Monome... pms) {
		pm = pms;
	}

	public static Polynome as(Monome... pms) {
		return new Polynome(pms);
	}

	@Override
	public Polynome multiply(Monome o) {
		if (o.p == 0)
			return as(Monome.as(0));
		List<Monome> res = new ArrayList<Monome>();
		boolean merged = false;
		for (Monome e : pm) {
			if (e.p == o.p) {
				res.add(Monome.as(e.p, e.n + o.n));
				merged = true;
			} else
				res.add(e);
		}
		if (!merged)
			res.add(o);
		return as(res.toArray(Monome[]::new));
	}

	@Override
	public Polynome multiply(Polynome o) {
		if (o.pm.length == 0)
			return this;
		if (pm.length == 0)
			return o;
		Polynome res = this;
		for (Monome e : o.pm) {
			res = res.multiply(e);
		}

		return res;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(value()).append(" = (");
		for (Monome e : pm) {
			sb.append(String.format("%d^%d", e.p, e.n)).append("x");
		}
		String s = sb.substring(0, sb.length() - 1);
		return s + ")";
	}

	@Override
	public Long value() {
		if (v == null)
			v = Arrays.stream(pm).map(p -> p.value()).reduce(1L, (a, b) -> a * b);
		return v;
	}

	/**
	 * @return
	 */
	public Monome[] monomes() {
		return pm;
	}
}
