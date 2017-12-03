package Functions;

import java.util.Random;

public class ConstruccionAleatoria {
	Random randomSeed;
	Sort sort;

	public ConstruccionAleatoria(int seed) {
		randomSeed = new Random(seed);
		sort = new Sort();
	}

	public int[] select(int n, int m) {
		int[] v = new int[n];
		for (int i = 0; i < n; i++) {
			v[i] = 0;
		}
		int r = 0;
		for (int i = 0; i < n; i++) {
			r = getRandom(m, v);
			v[i] = r;

		}
		return sort.ordenar(v);
	}

	public int getRandom(int m, int[] v) {

		int rnd = randomSeed.nextInt(m);

		int random = 0;
		for (int i = 0; i < v.length; i++) {
			if (v[i] == rnd) {
				random = getRandom(m, v);
				break;
			} else {
				random = rnd;
			}
		}

		return random;
	}

}
