package com.nouni.upwork.sjaak.prob1;

import java.util.TreeSet;

public class Set {

	protected java.util.Set<String> data = new TreeSet<>();

	public void add(String id) {
		data.add(id);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("{");
		data.forEach(s -> sb.append(s).append(" "));
		sb.append('}');
		return sb.toString();
	}

	/**
	 * 
	 * @param set2
	 * @return
	 */
	public Set diff(Set set2) {
		Set res = new Set();
		data.forEach(s -> {
			if (!set2.data.contains(s))
				res.add(s);
		});
		return res;
	}

	/**
	 * 
	 * @param set2
	 * @return
	 */
	public Set intersection(Set set2) {
		Set res = new Set();
		data.forEach(s -> {
			if (set2.data.contains(s))
				res.add(s);
		});
		set2.data.forEach(s -> {
			if (data.contains(s))
				res.add(s);
		});
		return res;
	}

	/**
	 * 
	 * @param set2
	 * @return
	 */
	public Set union(Set set2) {
		Set res = new Set();
		data.forEach(res::add);
		set2.data.forEach(res::add);
		return res;
	}

	/**
	 * 
	 * @param set2
	 * @return
	 */
	public Set symDiff(Set set2) {
		Set res = new Set();
		data.forEach(s -> {
			if (!set2.data.contains(s))
				res.add(s);
		});
		set2.data.forEach(s -> {
			if (!data.contains(s))
				res.add(s);
		});
		return res;
	}

}
