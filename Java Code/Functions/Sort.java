package Functions;

import java.util.ArrayList;

import DataStructures.Solucion;

public class Sort {

	public float[][] ordenarMatrix(float[][] vec) {
		for (int k = 0; k < (vec.length - 1); k++) {
			for (int f = 0; f < (vec.length - 1) - k; f++) {
				if (vec[f][1] > vec[f + 1][1]) {
					float[][] aux = new float[1][2];
					aux[0] = vec[f];
					vec[f] = vec[f + 1];
					vec[f + 1] = aux[0];
				}
			}
		}
		return vec;
	}

	public int[] ordenar(int[] vec) {
		for (int k = 0; k < (vec.length - 1); k++) {
			for (int f = 0; f < (vec.length - 1) - k; f++) {
				if (vec[f] > vec[f + 1]) {
					int aux;
					aux = vec[f];
					vec[f] = vec[f + 1];
					vec[f + 1] = aux;
				}
			}
		}
		return vec;
	}

	public ArrayList<Solucion> ordenarIndividuos(ArrayList<Solucion> poblacion) {
		float[][] aux = new float[poblacion.size()][2];
		for (int i = 0; i < aux.length; i++) {
			aux[i][0] = i;
			aux[i][1] = (float) poblacion.get(i).getDistance();
		}
		aux = ordenarMatrix(aux);

		ArrayList<Solucion> individuosOrdenados = new ArrayList<Solucion>();

		for (int i = 0; i < aux.length; i++) {
			int index = aux.length - 1 - i;
			Solucion individuo = poblacion.get((int) aux[index][0]);
			individuosOrdenados.add(individuo);
		}
		return individuosOrdenados;
	}

}
