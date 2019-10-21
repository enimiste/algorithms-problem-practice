package com.nouni.upwork.sjaak.prob2;

public class Main {
	public static void main(String[] args) {
		try {
			CommandLine cmd = new SortUniqCommandLine(args);
			SortUniq exe = new SortUniq(cmd.getArgs(), !cmd.hasOption('i'), cmd.hasOption('d'));
			exe.run(System.out);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
