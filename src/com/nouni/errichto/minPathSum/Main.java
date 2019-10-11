package com.nouni.errichto.minPathSum;

import java.util.function.BiFunction;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start");
		int[][] grid = new int[][] {
			{3, 2},
			{1, 9},
			//{9, 1, 5, 4}
		};
		int min = minPathSum(grid, 1, 1, Math::max);
		System.out.println("Min : " + min);
		System.out.println("End");
	}

	/**
	 * 
	 * @param grid
	 * @param row
	 * @param col
	 * @param agr
	 * @return
	 */
	private static int minPathSum(int[][] grid, int row, int col, BiFunction<Integer, Integer, Integer> agr) {
		int x = col - 1;
		int y = row - 1;
		int v = grid[row][col];
		if(x >= 0 || y >= 0) {
			if(x < 0) v += minPathSum(grid, y, col, agr);
			else if(y < 0) v += minPathSum(grid, row, x, agr);
			else v += agr.apply(minPathSum(grid, y, col, agr),  minPathSum(grid, row, x, agr));
		}
		return v;
	}

}
