package com.nouni.projecteuler.namesScores;

import java.io.*;
import java.util.*;
import java.util.stream.*;

//java 11 : 871198282 in 1.21 sec
public class Main {
    public static void main(String... args) throws Exception {
        long sm = sumNamesScores(names(new FileReader("./p022_names.txt")));
        System.out.println(sm);
    }

    static long sumNamesScores(List<String> names) {
        long sum = 0L;
        int nth = 1;
        for(String name : names) {
            sum += nameScore(alphaValue(name), nth++);
        }
        return sum;
    }

    static int nameScore(int alphaValue, int position) {
        return alphaValue * position;
    }

    static int alphaValue(String s) {
        //A : 65
        char[] cs = s.toCharArray();
        int sum = 0;
        for(char c : cs) {
            sum += (c - 65 + 1);
        }
        
        return sum;
    }

    static List<String> names(FileReader file) throws Exception {
        try(BufferedReader bfr = new BufferedReader(file)) {
            return bfr.lines()
                    .map(s -> s.split(","))
                    .flatMap(Arrays::stream)
                    .map(s -> s.replace("\"", "").toUpperCase())
                    .sorted()
                    .collect(Collectors.toList());
        }
    }
}