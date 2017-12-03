package Busqueda;

import java.util.ArrayList;
import java.util.Random;

import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.FuncionObjetivo;
import Functions.GenerateCombinations;
import Functions.Sort;

public class FirstImprovement {

	Instancia instancia;
	Sort sort;
	FuncionObjetivo fo;
	GenerateCombinations genComb;

	public FirstImprovement(Instancia instancia) {

		this.instancia = instancia;
		sort = new Sort();
		fo = new FuncionObjetivo();
		genComb = new GenerateCombinations();
	}

	public Solucion mejorar(Solucion solution) {
		Solucion fSolucion = solution;
		// imprimirSolucion(solution);

		int[] vSol = new int[instancia.getN()];
		for (int i = 0; i < vSol.length; i++) {
			vSol[i] = i;
		}

		// int[][] combSol = genComb.generate(vSol);

		Boolean continuar = true;
		while (continuar) {
			ArrayList<Integer> vecinos = posibleVecindad(fSolucion, instancia);
			int a = fSolucion.getMin()[0];
			int b = fSolucion.getMin()[1];

			int[] sol = new int[instancia.getN()];
			for (int k = 0; k < sol.length; k++) {
				sol[k] = fSolucion.getSelected()[k];
			}
			a = getIndex(a, sol);
			b = getIndex(b, sol);
			// System.out.println(a + "y" + b);
			// int r = combSol[j][0];
			// int r2 = combSol[j][1];
			int r = a;
			int r2 = b;
			int[] auxVec = null;
			if (vecinos.size() > 5) {
				auxVec = SeleccionarVecinos(ArrayLis2Vec(vecinos));
			} else {
				auxVec = ArrayLis2Vec(vecinos);
			}

			int[][] combinaciones = genComb.generate(ArrayLis2Vec(vecinos));

			for (int i = 0; i < combinaciones.length; i++) {

				sol[r] = combinaciones[i][0];
				sol[r2] = combinaciones[i][1];

				FuncionObjetivo f = new FuncionObjetivo();
				Solucion solAux = f.CalcularFuncionesObjetivo(instancia, sol);

				if (solAux.getDistance() > fSolucion.getDistance()) {
					fSolucion = solAux;
					// System.out.println("Nueva Solucion ");
					continuar = true;
					// imprimirSolucion(fSolucion);
					// j = 0;

					break;
				} else {
					if (i == combinaciones.length - 1) {
						continuar = false;
						// System.out.println("Minimo local");
					}

				}
			}

		}

		int[] mAux = fSolucion.getSelected();
		int[] mAux2 = sort.ordenar(mAux);
		Solucion finalSolucion = fo.CalcularFuncionesObjetivo(instancia, mAux2);
		return finalSolucion;

	}

	public int[] SeleccionarVecinos(int[] v) {
		int[] iVecinos = new int[5];
		int in = getRandom(v);
		for (int i = 0; i < iVecinos.length; i++) {
			iVecinos[i] = getRandomDistinto(iVecinos, v);
		}
		iVecinos = sort.ordenar(iVecinos);
		int[] vecinos = new int[5];
		for (int i = 0; i < vecinos.length; i++) {
			vecinos[i] = v[iVecinos[i]];
		}

		return vecinos;
	}

	public int getRandomDistinto(int[] ant, int[] v) {
		int r = 0;
		int repetido = 0;
		while (true) {
			r = getRandom(v);
			for (int i = 0; i < ant.length; i++) {
				if (r == ant[i]) {
					repetido += 1;
				}
			}
			if (repetido == 0) {
				break;
			}
			repetido = 0;

		}
		return r;
	}

	public void imprimirSolucion(Solucion solucion) {
		System.out.print("Funcion objetivo= " + solucion.getDistance() + " con |" + solucion.getMin()[0] + "|"
				+ solucion.getMin()[1] + "--");
		for (int i = 0; i < solucion.getSelected().length; i++) {
			System.out.print(solucion.getSelected()[i] + "|");
		}
		System.out.println("");

	}

	public ArrayList<Integer> posibleVecindad(Solucion solution, Instancia instancia) {
		int m = instancia.getM();
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			pos.add(i);
		}
		int[] sol = solution.getSelected();
		for (int i = 0; i < sol.length; i++) {
			pos = eliminar(sol[i], pos);
		}
		return pos;
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

	public int[] ArrayLis2Vec(ArrayList<Integer> v) {
		int[] m = new int[v.size()];
		for (int i = 0; i < m.length; i++) {
			m[i] = v.get(i);
		}
		return m;
	}

	public static int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return rnd;
	}

	public int getIndex(int v, int[] m) {
		int index = 0;
		for (int i = 0; i < m.length; i++) {
			if (v == m[i]) {
				index = i;
			}
		}
		return index;
	}

}
