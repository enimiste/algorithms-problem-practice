package com.nouni.upwork.sjaak.prob2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Cmd Args ::= (cmd options) files
 * Cmd options ::= option*
 * option ::= '-i' | '-d'
 * files ::= file (file)*
 * file ::= <file name>
 * 
 * @author HP
 *
 */
public class SortUniqCommandLine implements CommandLine {

	protected String[] originalArgs;
	protected Set<Character> options;
	protected String[] files = {};
	
	/**
	 * 
	 * @param args command line arguments (Arguments + options)
	 */
	public SortUniqCommandLine(String[] args) {
		originalArgs = args;
		options = new HashSet<>();
		parse();
	}
	
	private void parse() {
		int i = 0, origArgsSize = originalArgs.length;
		while(i < origArgsSize) {
			String arg = originalArgs[i]; 
			if(arg.startsWith("-")) {
				if(arg.compareToIgnoreCase("-i") == 0) {
					options.add('i');
				} else if(arg.compareToIgnoreCase("-d") == 0) {
					options.add('d');
				}
				i++;
			} else break;
		}
		List<String> files = new ArrayList<>();
		while(i < origArgsSize) {
			String arg = originalArgs[i];
			if(!arg.startsWith("-") || arg.isEmpty()) {
				files.add(arg);
			}
			i++;
		}
		this.files = files.toArray(String[]::new);
	}

	@Override
	public String[] getArgs() {
		return files;
	}

	@Override
	public boolean hasOption(char c) {
		return options.contains(c);
	}

}
