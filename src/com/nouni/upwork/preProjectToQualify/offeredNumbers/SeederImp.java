package com.nouni.upwork.preProjectToQualify.offeredNumbers;

public class SeederImp implements Seeder {

	protected SummaryImp summary;

	public SeederImp() {
		this.summary = new SummaryImp();
	}

	@Override
	public Summary getSummary() {
		return summary;
	}

	@Override
	public void seed(int number) {
		summary.visite(number);
	}
	
	static class SummaryImp implements Summary {
		protected int smalest;
		protected int largest;
		protected int count;
		protected double average;

		@Override
		public int getSmalest() {
			return smalest;
		}
		@Override
		public int getLargest() {
			return largest;
		}
		@Override
		public double getAverage() {
			return average;
		}

		void visite(int number) {
			/*
			 * a,b
			 * A1 = (a+b)/n1
			 * a,b,c
			 * A2 = (a+b+c)/n1 = (a+b)/n2 + c/n2 = (a+b)/n1*n1/n2 +c/n2 = A1*n1/n2+c/n2
			 * A2 = A1*n1/(n1+1) + c/(n1+1) = (A1*n1 + c)/(n1 + 1)
			 */
			if (count == 0) {
				smalest = number;
				average = smalest;
			} else if (count == 1) {
				smalest = Math.min(smalest, number);
				largest = Math.max(number, smalest);
				average = ((double)smalest + largest) / 2;
			} else {
				smalest = Math.min(smalest, number);
				largest = Math.max(largest, number);
				average = (average * count + number) / (count + 1);
			}
			count++;
		}

	}

}
