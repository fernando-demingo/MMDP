package Functions;

public class Statistics {

	public float media(float[] v) {
		int l = v.length;
		float media = 0.0f;
		for (int i = 0; i < l; i++) {
			media += v[i];
		}
		media = media / l;
		return media;
	}

	public double desviacionEstandar(float[] v) {
		int n = v.length;
		float med = media(v);
		// System.out.println("media=" + med);
		double sd = 0.0f;
		for (int i = 0; i < n; i++) {
			sd += Math.pow(v[i] - med, 2);
		}
		sd = sd / ((double) n);
		sd = Math.sqrt(sd);
		// System.out.println("sd=" + sd);
		return sd;
	}

	public float mediana(float v[]) {
		int l = v.length;
		v = ordenar(v);
		float mediana;
		if (esImpar(l)) {
			int i = Math.round(((float) l) / 2.0f);
			mediana = v[i - 1];
		} else {
			int i = Math.round(((float) l) / 2.0f);
			mediana = (v[i - 1] + v[i]) / 2.0f;
		}

		return mediana;
	}

	public boolean esImpar(int v) {
		boolean impar = false;
		float a = ((float) v) / 2.0f;
		float b = (float) Math.floor(a);
		if (a - b > 0.0f) {
			impar = true;
		}
		return impar;
	}

	public float[] ordenar(float[] vec) {
		for (int k = 0; k < (vec.length - 1); k++) {
			for (int f = 0; f < (vec.length - 1) - k; f++) {
				if (vec[f] > vec[f + 1]) {
					float aux;
					aux = vec[f];
					vec[f] = vec[f + 1];
					vec[f + 1] = aux;
				}
			}
		}
		return vec;
	}
}
