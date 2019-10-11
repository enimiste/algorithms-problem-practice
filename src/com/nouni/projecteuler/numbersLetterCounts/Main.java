package com.nouni.projecteuler.numbersLetterCounts;

import java.util.*;

//java 11 : 21124 in 1.239 secs
public class Main {

  static final Map<Integer, L> LETTERS = new HashMap();

  static class L {
      String txt;
      int size;

      static L as(String s) {
          L l = new L();
          l.txt = s;
          l.size = s.length();
          return l;
      }
  }
  static {
      LETTERS.put(1, L.as("one"));
      LETTERS.put(2, L.as("two"));
      LETTERS.put(3, L.as("three"));
      LETTERS.put(4, L.as("four"));
      LETTERS.put(5, L.as("five"));
      LETTERS.put(6, L.as("six"));
      LETTERS.put(7, L.as("seven"));
      LETTERS.put(8, L.as("eight"));
      LETTERS.put(9, L.as("nine"));
      LETTERS.put(10, L.as("ten"));
      LETTERS.put(11, L.as("eleven"));
      LETTERS.put(12, L.as("twelve"));
      LETTERS.put(13, L.as("thirteen"));
      LETTERS.put(14, L.as("fourteen"));
      LETTERS.put(15, L.as("fifteen"));
      LETTERS.put(16, L.as("sixteen"));
      LETTERS.put(17, L.as("seventeen"));
      LETTERS.put(18, L.as("eighteen"));
      LETTERS.put(19, L.as("nineteen"));
      LETTERS.put(20, L.as("twenty"));
      LETTERS.put(30, L.as("thirty"));
      LETTERS.put(40, L.as("forty"));
      LETTERS.put(50, L.as("fifty"));
      LETTERS.put(60, L.as("sixty"));
      LETTERS.put(70, L.as("seventy"));
      LETTERS.put(80, L.as("eighty"));
      LETTERS.put(90, L.as("ninety"));
  }

  public static void main(String... args) {
      int sum = 0, max = 1000;
      for(int i = 1; i <= max; i++) {
          try {
              //sum += lettersCount(asString(i)); in 1.292 sec
              sum += lettersCount2(i);// in 1.239 sec
          } catch(Exception e) {
              throw new RuntimeException("At " + i, e);
          }
      }
      System.out.println(sum);
  }

  static int lettersCount(String n) {
      return n.replace("-", "").replace(" ", "").length();
  }

  static String asString(int n) {
      if(LETTERS.containsKey(n)) return LETTERS.get(n).txt;
      String[] digits = String.valueOf(n).split("");
      if(digits.length == 2) {
          String s1 = LETTERS.get(Integer.valueOf(digits[0] + "0")).txt;
          String s2 = LETTERS.get(Integer.valueOf(digits[1])).txt;
          return s1 + "-" + s2;
      } else if(digits.length == 3) {
          String s0 = LETTERS.get(Integer.valueOf(digits[0])).txt;
          String s1 = "hundred";

          Integer ss = Integer.valueOf(digits[1] + digits[2]);
          if(LETTERS.containsKey(ss)) {
              
              return s0 + " " + s1 + " and " + LETTERS.get(ss).txt;
          } else {
              Integer i1 =  Integer.valueOf(digits[1]);
              Integer i2 = Integer.valueOf(digits[2]);
              if(i1 == 0 && i2 == 0) {
                  return s0 + " " + s1;
              } else {
                  String s2 = (i1 == 0) ? "" : LETTERS.get(Integer.valueOf(i1 + "0")).txt;
              
                  String s3 = (i2 == 0) ? "" : ((i1 == 0 ? "" : "-") + LETTERS.get(i2).txt);
                  return s0 + " " + s1 + " and " + s2 + s3;
              }
              
          }
      } else if(digits.length == 4) {
          String s0 = LETTERS.get(Integer.valueOf(digits[0])).txt;
          String s1 = "thousand";
          return s0 + " " + s1;
      }
      return "";
  }

  static int lettersCount2(int n) {
      if(LETTERS.containsKey(n)) return LETTERS.get(n).size;
      String[] digits = String.valueOf(n).split("");
      if(digits.length == 2) {
          int s1 = LETTERS.get(Integer.valueOf(digits[0] + "0")).size;
          int s2 = LETTERS.get(Integer.valueOf(digits[1])).size;
          return s1 + s2;
      } else if(digits.length == 3) {
          int s0 = LETTERS.get(Integer.valueOf(digits[0])).size;
          int s1 = 7;//"hundred";

          Integer ss = Integer.valueOf(digits[1] + digits[2]);
          if(LETTERS.containsKey(ss)) {
              //and : 3 letters
              return s0 + s1 + 3 + LETTERS.get(ss).size;
          } else {
              Integer i1 =  Integer.valueOf(digits[1]);
              Integer i2 = Integer.valueOf(digits[2]);
              if(i1 == 0 && i2 == 0) {
                  return s0 + s1;
              } else {
                  int s2 = (i1 == 0) ? 0 : LETTERS.get(Integer.valueOf(i1 + "0")).size;
              
                  int s3 = (i2 == 0) ? 0 : LETTERS.get(i2).size;
                  return s0 + s1 + 3 + s2 + s3;
              }
              
          }
      } else if(digits.length == 4) {
          int s0 = LETTERS.get(Integer.valueOf(digits[0])).size;
          int s1 = 8;//"thousand";
          return s0 + s1;
      }
      return 0;
  }
}