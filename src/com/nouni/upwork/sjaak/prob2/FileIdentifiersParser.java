package com.nouni.upwork.sjaak.prob2;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.function.Function;

//TEXT ::= IDENTIFIER|NON-IDENTIFIER|DELIMITER
//IDENTIFIER ::= LETTER (LETTER | DIGIT)*
//NON-IDENTIFIER ::= DIGIT (LETTER | DIGIT)*
//LETTER ::= [A-Za-z] DIGIT ::= [0-9]
//DELIMITTER ::= [^A-Za-z0-9]
public class FileIdentifiersParser {

	public void reset() {
		
	}

	/**
	 * 
	 * @param path
	 * @param onIdentifierFound
	 * @throws Exception if the file doesn't exists
	 */
	public void parse(String path, Function<String, Boolean> onIdentifierFound) throws Exception {
		try(FileInputStream input = new FileInputStream(path);
				Scanner scan = new Scanner(input)) {
			scan.useDelimiter("[^A-Za-z0-9]");
			while(scan.hasNext()) {
				String token = scan.next();
				if(!token.isBlank()) {
					if(token.matches("^[A-Za-z][A-Za-z0-9]*$")) {
						onIdentifierFound.apply(token);
					}
				}
			}
			
		}
	}

}
