package Solutions;

import java.util.ArrayList;
import java.util.Random;

import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.FuncionObjetivo;
import Functions.MinMax;
import Functions.Sort;
import Functions.Statistics;

public class SolucionVoraz {
	Statistics statistics;
	Sort sort;
	FuncionObjetivo fo;
	MinMax minMax;

	public SolucionVoraz() {
		statistics = new Statistics();
		sort = new Sort();
		fo = new FuncionObjetivo();
		minMax = new MinMax();
	}

	public Solucion generateSolution1(Instancia instancia, boolean print) {
		int lenght = instancia.getInstanceMatrix().length;
		float[][] results = new float[lenght][2];
		for (int i = 0; i < lenght; i++) {
			float[] v = new float[lenght];
			for (int j = 0; j < lenght; j++) {
				v[j] = instancia.getValueAt(i, j);
			}
			results[i][0] = i;
			results[i][1] = statistics.media(v) - (float) statistics.desviacionEstandar(v);
		}
		results = sort.ordenarMatrix(results);
		int[] s = new int[instancia.getN()];
		int aux = 0;
		for (int i = lenght - instancia.getN(); i < lenght; i++) {
			s[aux] = (int) results[i][0];
			aux++;
		}
		s = sort.ordenar(s);
		FuncionObjetivo fo = new FuncionObjetivo();
		Solucion solution = fo.CalcularFuncionesObjetivo(instancia, s);

		if (print == true) {
			System.out.println("Solucion voraz 1 con Fo: " + solution.getDistance());
			System.out.print("usando la seleccion:");
			for (int j = 0; j < solution.getSelected().length; j++) {
				System.out.print(solution.getSelected()[j] + "|");
			}
			System.out.print("\n");
		}

		return solution;

	}

	public Solucion generateSolution2(Instancia instancia, boolean print) {

		int lenght = instancia.getInstanceMatrix().length;
		float[][] results = new float[lenght][2];
		for (int i = 0; i < lenght; i++) {
			float[] v = new float[lenght];
			for (int j = 0; j < lenght; j++) {
				v[j] = instancia.getValueAt(i, j);
			}
			results[i][0] = i;
			results[i][1] = statistics.mediana(v) - (float) statistics.desviacionEstandar(v);
		}

		results = sort.ordenarMatrix(results);
		int[] s = new int[instancia.getN()];
		int aux = 0;
		for (int i = lenght - instancia.getN(); i < lenght; i++) {
			s[aux] = (int) results[i][0];
			aux++;
		}
		s = sort.ordenar(s);

		FuncionObjetivo fo = new FuncionObjetivo();
		Solucion solution = fo.CalcularFuncionesObjetivo(instancia, s);

		if (print == true) {
			System.out.println("Solucion voraz 2 con Fo: " + solution.getDistance());
			System.out.print("usando la seleccion:");
			for (int j = 0; j < solution.getSelected().length; j++) {
				System.out.print(solution.getSelected()[j] + "|");
			}
			System.out.print("\n");
		}

		return solution;

	}

	public Solucion generateSolution3(Instancia instancia, boolean print) {
		int m = instancia.getM();
		int n = instancia.getN();
		int sol[] = new int[n];
		ArrayList<Integer> posibSolition = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			posibSolition.add(i);
		}
		float[][] minimos = new float[m][2];
		float matrix[][] = new float[m][m];

		for (int i = 0; i < m; i++) {
			float aux[][] = new float[m][2];
			for (int j = 0; j < m; j++) {
				aux[j][0] = j;
				aux[j][1] = instancia.getValueAt(i, j);
				matrix[i][j] = instancia.getValueAt(i, j);
			}
			aux = sort.ordenarMatrix(aux);

			minimos[i][1] = primeroDiferente(aux)[0][1];
			minimos[i][0] = i;

		}

		minimos = sort.ordenarMatrix(minimos);
		int iniciar = (int) minimos[m - 1][0];

		sol[0] = iniciar;
		float aux[][] = new float[m][2];
		for (int j = 0; j < m; j++) {
			aux[j][0] = j;
			aux[j][1] = matrix[iniciar][j];
		}
		aux = sort.ordenarMatrix(aux);

		sol[1] = (int) aux[m - 1][0];

		posibSolition = eliminar(sol[0], posibSolition);
		posibSolition = eliminar(sol[1], posibSolition);

		for (int i = 2; i < n; i++) {
			int[] ant = new int[i + 1];
			for (int j = 0; j < ant.length; j++) {
				ant[j] = sol[j];
			}
			ArrayList<Solucion> results = new ArrayList<Solucion>();
			for (int j = 0; j < posibSolition.size(); j++) {
				int value = posibSolition.get(j);
				ant[i] = value;
				Solucion sTest = fo.CalcularFuncionesObjetivo(instancia, ant.clone());
				results.add(sTest);
			}

			Solucion finalSol = minMax.getMax(results);
			int[] finSol = finalSol.getSelected();
			posibSolition = eliminar(finSol[finSol.length - 1], posibSolition);
			finSol = sort.ordenar(finSol);
			for (int j = 0; j < finSol.length; j++) {
				sol[j] = finSol[j];
			}

		}
		sol = sort.ordenar(sol);
		FuncionObjetivo fo = new FuncionObjetivo();
		Solucion solution = fo.CalcularFuncionesObjetivo(instancia, sol);
		if (print == true) {
			System.out.println("Solucion voraz 3 con Fo: " + solution.getDistance());
			System.out.print("usando la seleccion:");
			for (int j = 0; j < solution.getSelected().length; j++) {
				System.out.print(solution.getSelected()[j] + "|");
			}
			System.out.print("\n");
		}
		return solution;

	}

	public Solucion generateSolution4(Instancia instancia, boolean print) {
		int m = instancia.getM();
		int n = instancia.getN();
		int sol[] = new int[n];
		ArrayList<Integer> posibSolition = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			posibSolition.add(i);
		}

		float matrix[][] = new float[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = instancia.getValueAt(i, j);
			}

		}

		int iniciar = getRandom(m);

		sol[0] = iniciar;
		float aux[][] = new float[m][2];
		for (int j = 0; j < m; j++) {
			aux[j][0] = j;
			aux[j][1] = matrix[iniciar][j];

		}

		aux = sort.ordenarMatrix(aux);

		sol[1] = (int) aux[m - 1][0];

		posibSolition = eliminar(sol[0], posibSolition);
		posibSolition = eliminar(sol[1], posibSolition);

		for (int i = 2; i < n; i++) {
			int[] ant = new int[i + 1];
			for (int j = 0; j < ant.length; j++) {
				ant[j] = sol[j];
			}
			ArrayList<Solucion> results = new ArrayList<Solucion>();
			for (int j = 0; j < posibSolition.size(); j++) {
				int value = posibSolition.get(j);
				ant[i] = value;
				Solucion sTest = fo.CalcularFuncionesObjetivo(instancia, ant.clone());
				results.add(sTest);
			}

			Solucion finalSol = minMax.getMax(results);
			int[] finSol = finalSol.getSelected();
			posibSolition = eliminar(finSol[finSol.length - 1], posibSolition);
			finSol = sort.ordenar(finSol);
			for (int j = 0; j < finSol.length; j++) {
				sol[j] = finSol[j];
			}

		}
		sol = sort.ordenar(sol);
		FuncionObjetivo fo = new FuncionObjetivo();
		Solucion solution = fo.CalcularFuncionesObjetivo(instancia, sol);
		if (print == true) {
			System.out.println("Solucion voraz 4 con Fo: " + solution.getDistance());
			System.out.print("usando la seleccion:");
			for (int j = 0; j < solution.getSelected().length; j++) {
				System.out.print(solution.getSelected()[j] + "|");
			}
			System.out.print("\n");
		}
		return solution;

	}

	public float[][] primeroDiferente(float[][] m) {
		float[][] res = new float[1][2];
		for (int i = 0; i < m.length; i++) {
			if (m[i][1] != 0.0f) {
				res[0][0] = m[i][0];
				res[0][1] = m[i][1];
				break;
			}
		}
		return res;
	}

	public ArrayList<Integer> eliminar(int v, ArrayList<Integer> list) {
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			int aux = list.get(i);
			if (aux == v) {
				index = i;
			}
		}
		list.remove(index);
		return list;
	}

	public static int getRandom(int num) {
		int rnd = new Random().nextInt(num);
		return rnd;
	}

}
