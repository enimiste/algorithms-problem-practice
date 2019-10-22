package com.nouni.upwork.sjaak.prob2;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.Iterator;

public class SortUniq {

	protected String[] files;
	protected boolean caseSensitive;
	protected boolean nonIncresingOrder;
	protected Set set;

	public SortUniq(String[] files, boolean caseSensitive, boolean nonIncresingOrder) {
		if (files == null || files.length == 0)
			throw new IllegalArgumentException("You should provide at least one file to parse");
		this.files = files;
		this.caseSensitive = caseSensitive;
		this.nonIncresingOrder = nonIncresingOrder;
	}

	public void run(PrintStream out) throws Exception {
		if (out == null)
			throw new IllegalArgumentException("You should provide a non null PrintStream.");
		set = new BinarySearchTree(new CustomStringComparator(caseSensitive));

		FileIdentifiersParser parser = new FileIdentifiersParser();
		for (String path : files) {
			parser.reset();
			parser.parse(path, set::add);
		}
		// Show found identifiers
		Iterator<String> iter = nonIncresingOrder ? set.descendingIterator() : set.ascendingIterator();
		while (iter.hasNext()) {
			report(iter.next(), out);
		}
	}

	/**
	 * 
	 * @param s
	 * @param out
	 */
	private void report(String s, PrintStream out) {
		if (s == null || caseSensitive)
			out.println(s);
		else
			out.println(s.toLowerCase());
	}

	/**
	 * Comparator to be used depends on the option used in the command line
	 * 
	 * @author HP
	 *
	 */
	static class CustomStringComparator implements Comparator<String> {

		private boolean caseSensitive;

		CustomStringComparator(boolean caseSensitive) {
			this.caseSensitive = caseSensitive;
		}

		@Override
		public int compare(String s1, String s2) {
			if (s1 == null && s2 == null)
				return 0;
			else if (s1 != null) {
				return caseSensitive ? s1.compareTo(s2) : s1.compareToIgnoreCase(s2);
			} else {
				return caseSensitive ? s2.compareTo(s1) : s2.compareToIgnoreCase(s1);
			}
		}

	}

}
