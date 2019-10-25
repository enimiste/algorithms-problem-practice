package com.nouni.upwork.preProjectToQualify.offeredNumbers;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Seeder seeder = new SeederImp();
		(new Random()).ints(0, Integer.MAX_VALUE).limit(99999).forEach(seeder::seed);
		Summary summary = seeder.getSummary();
		System.out.println("Smalest number : " + summary.getSmalest());
		System.out.println("Largest number : " + summary.getLargest());
		System.out.println(String.format("Average of numbers : %.2f",summary.getAverage()));
	}
}
