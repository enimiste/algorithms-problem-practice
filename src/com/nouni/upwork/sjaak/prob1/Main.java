package com.nouni.upwork.sjaak.prob1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    /*
     * As explained during the lecture your program for assignment 1 will have 4
     * nested iterations. The following structured design shows how you could deal
     * with the first 2 of these 4 iterations.
     */

    static final int MAX_NUMBER_OF_ELEMENTS = 10;

    PrintStream out;

    boolean askSet(Scanner input, String question, Set set) {
        do {
            out.printf("%s", question);
            if (!input.hasNextLine()) {
                out.printf("\n"); // otherwise line with ^D will be overwritten
                return false;
            }
        } while (!inputContainsCorrectSet(input, set));
        return true;
    }

    boolean askBothSets(Scanner input, Set set1, Set set2) {
        return askSet(input, "Give first set : ", set1) && askSet(input, "Give second set : ", set2);
    }


    /* The method inputContainsCorrectSet(Scanner input, Set set) should, while
       reading the input (the answer), check whether the input is correct.

       If the input is not correct this method should (1) give an error explaining
       what was wrong with the input, (2) skip the remaining characters of the input
       and (3) return false.

       If the input is correct this method should (1) assign to the parameter set
       the value of the set of identifiers on the input and (2) return true.

       N.B. Do not try to give too intelligent errors. This is not an exercise in
            artificial intelligence.
            For instance, if the input is "{abc def} gh" instead of "{abc def gh}",
            an (relatively easy) error like "no input allowed after '}' is fine. You
            don't have to write a program that seems to understand what you were
            trying to do and would give an error like "by accident you put the '}'
            before the last identifier instead of after it. Please correct this."
    */
    private boolean inputContainsCorrectSet(Scanner input, Set set) {
        try {
            String line = input.nextLine() + "|";//| EOL character; to avoid blocking on input when checking that extra chars exists after }
            Scanner in = new Scanner(line);
            in.useDelimiter("");
            ignoreSpaces(in);
            if (!nextCharIs(in, '{')) {
                throw new RuntimeException("Set definition should begin with an opening bracket");
            }
            nextChar(in);//to move to next char
            ignoreSpaces(in);
            if (!nextCharIs(in, '|') && !nextCharIs(in, '}')) {
                Identifier id = identifier(in);
                set.add(id);
                do {
                    ignoreSpaces(in);
                    if (!nextCharIs(in, '|') && !nextCharIs(in, '}')) {
                        id = identifier(in);
                        if (id != null) {
                            set.add(id);
                        } else {
                            throw new RuntimeException("Invalid identifier found. [A-Za-z][A-Za-z0-9]*");
                        }
                    } else break;
                } while (true);
            }
            ignoreSpaces(in);
            if (!nextCharIs(in, '}')) {
                throw new RuntimeException("Set definition should end with a closing bracket");
            }
            nextChar(in);//to move to next char
            ignoreSpaces(in);
            if (!nextCharIs(in, '|') && nextCharIsLetter(in)) {
                throw new RuntimeException("no input allowed after '}'");
            }
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            error(e.getMessage());
            return false;
        }
    }

    private Identifier identifier(Scanner input) {
        if (nextCharIsLetter(input)) {
            StringBuffer sb = new StringBuffer();
            sb.append(nextChar(input));
            while (nextCharIsLetter(input) || nextCharIsDigit(input)) {
                sb.append(nextChar(input));
            }
            return Identifier.fromString(sb.toString());
        } else throw new RuntimeException("Identifier should begin with a Letter.");

    }

    /*
     * EBNF :
     * ======
     * SET 		::= '{' ELEMS '}'
     * ELEMS 	::= e | ID (',' ID)*
     * ID 		::= LETTER (LETTER | NUMBER)*
     * LETTER	::= [A-Za-z]
     * NUMBER	::= [0-9]
     */

    char nextChar(Scanner in) {
        char c = in.next().charAt(0);
        return c;
    }

    boolean nextCharIs(Scanner in, char c) {
        return in.hasNext(Pattern.quote(c + ""));
    }

    void ignoreSpaces(Scanner in) {
        while (in.hasNext("\\s")) in.next();
    }

    boolean nextCharIsDigit(Scanner in) {
        return in.hasNext("[0-9]");
    }

    boolean nextCharIsLetter(Scanner in) {
        return in.hasNext("[a-zA-Z]");
    }

    private void error(String msg) {
        out.println(msg);
    }

    private void calculateAndGiveOutput(Set set1, Set set2) {
        out.println("Set A = " + set1);
        out.println("Set B = " + set2);
        out.println("difference (A - B): " + set1.diff(set2));
        out.println("intersection (A*B): " + set1.intersection(set2));
        out.println("union (A+B): " + set1.union(set2));
        out.println("sym. diff (A|B): " + set1.symDiff(set2));
    }

    void start() {
        out = System.out;
        Scanner in = new Scanner(System.in);
        Set set1 = new SetArrayBasedImpl(MAX_NUMBER_OF_ELEMENTS);
        Set set2 = new SetArrayBasedImpl(MAX_NUMBER_OF_ELEMENTS);

        while (askBothSets(in, set1, set2)) {
            calculateAndGiveOutput(set1, set2);
            break;
        }
    }

    public static void main(String[] args) {
        //GIve first set : {A B C}
        (new Main()).start();
    }

}
