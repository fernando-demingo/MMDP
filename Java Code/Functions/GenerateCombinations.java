package Functions;

import java.util.ArrayList;

public class GenerateCombinations {

	public int[][] generate(int[] v) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		int m = v.length;
		int n = 2;
		// double nC = Factorial(m) / (Factorial(n) * Factorial(m - n));
		// int nc = (int) nC;
		// int[][] combinaciones = new int[nc][2];

		/*
		 * int aux = 0; for (int i = 0; i < m - 1; i++) { for (int j = i + 1; j
		 * < m; j++) { combinaciones[aux][0] = v[i]; combinaciones[aux][1] =
		 * v[j]; aux++; } }
		 */

		for (int i = 0; i < m - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				int auxA = v[i];
				int auxB = v[j];
				a.add(auxA);
				b.add(auxB);
			}
		}
		int l = a.size();
		int[][] combinaciones = new int[l][2];
		for (int i = 0; i < l; i++) {
			combinaciones[i][0] = a.get(i);
			combinaciones[i][1] = b.get(i);
		}

		return combinaciones;
	}

	public double Factorial(int numero) {
		double factorial = 1;
		while (numero != 0) {
			factorial = factorial * numero;
			numero--;
		}
		return factorial;
	}
}
