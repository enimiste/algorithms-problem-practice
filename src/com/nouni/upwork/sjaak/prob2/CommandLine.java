package com.nouni.upwork.sjaak.prob2;

/**
 * Command line arguments parser
 * @author HP
 *
 */
public interface CommandLine {

	/**
	 * 
	 * @return arguments except options
	 */
	String[] getArgs();

	boolean hasOption(char c);

}
