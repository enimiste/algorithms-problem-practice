package com.nouni.projecteuler.sumSquareDifference;

public class Main {
	public static void main(String[] args) {
		System.out.println(sumSquare(2));
	}
	
	static long sum(int n) {
		return (long)(n+1)*n/2;
	}
	
	static long sumSquare(int n) {
		return (long)(n*n*n/3+n*n/2+n/6);
	}
}
