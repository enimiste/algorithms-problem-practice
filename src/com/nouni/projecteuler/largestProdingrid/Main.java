package com.nouni.projecteuler.largestProdingrid;

public class Main {

	public static void main(String[] args) {
		System.out.println(findLargeProd(grid(), 20, 4));

	}

	/**
	 * 
	 * @param grid
	 * @param n
	 * @param s
	 * @return
	 */
	static R findLargeProd(int[][] grid, int n, int s) {
		int i = 0, j = 0;
		R lp = R.as(0L, s);
		R p;
		while (true) {

			// for a given (i,j)
			p = maxProdFromAPoint(grid, n, i, j, s);
			if (p.p > lp.p)
				lp = p;
			j++;
			if (j >= n) {
				j = 0;
				i++;
			}
			if (i >= n)
				break;
		}
		return lp;
	}

	/**
	 * up, down, left, right, or diagonally
	 * 
	 * @param grid
	 * @param n    grid size (Square grid)
	 * @param i    row index in the grid
	 * @param j    column index in the grid
	 * @param s    windows size
	 * 
	 * @return array of 8 elements
	 */
	static R maxProdFromAPoint(int[][] grid, int n, int i, int j, int s) {
		R max = R.as(0L, s);
		R tmp = R.as(1L, s);
		int k;
		int t;
		/* mini, maxi, minj, maxj */
		int[][] sides = new int[][] {
				new int[] { i, i, j, j + s - 1 }, // right
				new int[] { i, i + s - 1, j, j + s - 1 }, // diag down right
				new int[] { i, i + s - 1, j, j },// down
				new int[] { i, i + s - 1, j - s + 1 , j},// diag down left
		};
		// right, diag down right and down can scan all of the grid
		for (int[] side : sides) {
			check(side);
			tmp.reset(1L);
			if (side[2] == side[3]) {
				for (k = side[0]; k <= side[1]; k++) {
					tmp.multi(k, side[2], getValueFromGrid(grid, n, k, side[2], 0));
					if (tmp.p == 0)
						break;
				}
			} else if (side[0] == side[1]) {
				for (k = side[2]; k <= side[3]; k++) {
					tmp.multi(side[0], k, getValueFromGrid(grid, n, side[0], k, 0));
					if (tmp.p == 0)
						break;
				}
			} else {
				if(j <= side[2]) {
					k = side[0];
					t = side[2];
					for (; k <= side[1] & t <= side[3];) {
						tmp.multi(k, t, getValueFromGrid(grid, n, k, t, 0));
						if (tmp.p == 0)
							break;
						 k++;
						 t++;
					}
				} else {
					k = side[0];
					t = side[3];
					for (; k <= side[1] & t >= side[2];) {
						tmp.multi(k, t, getValueFromGrid(grid, n, k, t, 0));
						if (tmp.p == 0)
							break;
						 k++;
						 t--;
					}
				}
			}
			//if(tmp.p != 0) System.out.println(tmp);
			if (max.p < tmp.p)
				max.update(tmp);
		}
		return max;
	}
	
	static void check(int[] x) {
		if(x.length != 4 || x[0] > x[1] || x[2] > x[3]) throw new RuntimeException(String.format("Invalid sides description : [%d, %d, %d, %d]", x[0], x[1], x[2], x[3]));
	}

	/**
	 * 
	 * @param grid
	 * @param n
	 * @param i
	 * @param j
	 * @param defau value to return if i and/or j aren't a valid indexes
	 * @return
	 */
	static int getValueFromGrid(int[][] grid, int n, int i, int j, int defau) {
		if (i < 0 || j < 0 || i >= n || j >= n)
			return defau;
		return grid[i][j];
	}

	static int[][] grid() {
		int[][] g = new int[][] { 
				new int[] { 8, 2, 22, 97, 38, 15, 0, 40, 0, 75, 4, 5, 7, 78, 52, 12, 50, 77, 91, 8 },
				new int[] { 49, 49, 99, 40, 17, 81, 18, 57, 60, 87, 17, 40, 98, 43, 69, 48, 4, 56, 62, 0 },
				new int[] { 81, 49, 31, 73, 55, 79, 14, 29, 93, 71, 40, 67, 53, 88, 30, 3, 49, 13, 36, 65 },
				new int[] { 52, 70, 95, 23, 4, 60, 11, 42, 69, 24, 68, 56, 1, 32, 56, 71, 37, 2, 36, 91 },
				new int[] { 22, 31, 16, 71, 51, 67, 63, 89, 41, 92, 36, 54, 22, 40, 40, 28, 66, 33, 13, 80 },
				new int[] { 24, 47, 32, 60, 99, 3, 45, 2, 44, 75, 33, 53, 78, 36, 84, 20, 35, 17, 12, 50 },
				new int[] { 32, 98, 81, 28, 64, 23, 67, 10, 26, 38, 40, 67, 59, 54, 70, 66, 18, 38, 64, 70 },
				new int[] { 67, 26, 20, 68, 2, 62, 12, 20, 95, 63, 94, 39, 63, 8, 40, 91, 66, 49, 94, 21 },
				new int[] { 24, 55, 58, 5, 66, 73, 99, 26, 97, 17, 78, 78, 96, 83, 14, 88, 34, 89, 63, 72 },
				new int[] { 21, 36, 23, 9, 75, 0, 76, 44, 20, 45, 35, 14, 0, 61, 33, 97, 34, 31, 33, 95 },
				new int[] { 78, 17, 53, 28, 22, 75, 31, 67, 15, 94, 3, 80, 4, 62, 16, 14, 9, 53, 56, 92 },
				new int[] { 16, 39, 5, 42, 96, 35, 31, 47, 55, 58, 88, 24, 0, 17, 54, 24, 36, 29, 85, 57 },
				new int[] { 86, 56, 0, 48, 35, 71, 89, 7, 5, 44, 44, 37, 44, 60, 21, 58, 51, 54, 17, 58 },
				new int[] { 19, 80, 81, 68, 5, 94, 47, 69, 28, 73, 92, 13, 86, 52, 17, 77, 4, 89, 55, 40 },
				new int[] { 04, 52, 8, 83, 97, 35, 99, 16, 7, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66 },
				new int[] { 88, 36, 68, 87, 57, 62, 20, 72, 3, 46, 33, 67, 46, 55, 12, 32, 63, 93, 53, 69 },
				new int[] { 04, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18, 8, 46, 29, 32, 40, 62, 76, 36 },
				new int[] { 20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69, 82, 67, 59, 85, 74, 4, 36, 16 },
				new int[] { 20, 73, 35, 29, 78, 31, 90, 1, 74, 31, 49, 71, 48, 86, 81, 16, 23, 57, 5, 54 },
				new int[] { 01, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48, 61, 43, 52, 1, 89, 19, 67, 48 } };
		return g;
	}

	static void showArr(long[] arr) {
		System.out.print("[");
		for (long i : arr) {
			System.out.print(i + ",");
		}
		System.out.println("]");
	}
	
	/**
	 * Result serie element
	 * Used by the R class
	 * @author e.nouni
	 *
	 */
	static class S {
		public int v;
		public int i;
		public int j;
		
		static S as(int i, int j, int v) {
			S x = new S();
			x.v = v;
			x.i = i;
			x.j = j;
			return x;
		}
		
		@Override
		public String toString() {
			return String.format("(%d, %d, %d)", i, j, v);
		}
	} 
	
	/**
	 * Result
	 * @author e.nouni
	 *
	 */
	static class R {
		public long p;
		public S[] serie;
		protected int idx = 0;
		
		static R as(long p, int s) {
			R x = new R();
			x.p = p;
			x.serie = new S[s];
			return x;
		}
		
		void multi(int i, int j, int v) {
			p *= v;
			if(p < 0) throw new RuntimeException("Overflow " + this);
			serie[idx++] = S.as(i, j, v);
		}
		
		void update(R r) {
			p = r.p;
			serie = r.serie;
			idx = r.idx;
		}
		
		void reset(long p) {
			this.p = p;
			serie = new S[serie.length];
			idx = 0;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(p).append(" : [");
			for(S s : serie) {
				sb.append(s).append(", ");
			}
			sb.append("]");
			return sb.toString();
		}
	}
}
