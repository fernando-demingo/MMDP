package Solutions;

import java.util.ArrayList;
import java.util.Random;

import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.FuncionObjetivo;
import Functions.Sort;

public class Alterar {
	FuncionObjetivo fo;
	Sort sort;

	public Alterar() {
		fo = new FuncionObjetivo();
		sort = new Sort();
	}

	public ArrayList<Solucion> Cruce(Instancia instancia, Solucion sol1, Solucion sol2) {
		int[] gen1 = sol1.getSelected();
		int[] gen2 = sol2.getSelected();
		ArrayList<Integer> total = new ArrayList<>();
		int corte = getRandom(gen1.length - 1);
		int[] total1 = new int[gen1.length];
		int[] total2 = new int[gen2.length];

		for (int i = 0; i < corte + 1; i++) {
			total1[i] = gen1[i];
			total2[i] = gen2[i];
		}
		for (int i = corte + 1; i < gen1.length; i++) {
			total1[i] = gen2[i];
			total2[i] = gen1[i];
		}

		total1 = sort.ordenar(total1);
		total2 = sort.ordenar(total2);
		Solucion nSol1 = fo.CalcularFuncionesObjetivo(instancia, total1);
		Solucion nSol2 = fo.CalcularFuncionesObjetivo(instancia, total2);
		ArrayList<Solucion> newIndividuos = new ArrayList<Solucion>();
		newIndividuos.add(nSol1);
		newIndividuos.add(nSol2);
		return newIndividuos;

	}

	public Solucion Mutar(Instancia instancia, Solucion sol, double mutationRate) {
		int[] fenotipo = sol.getSelected();
		for (int i = 0; i < fenotipo.length; i++) {
			double ran = new Random().nextDouble();
			int randGen = getRandom(instancia.getM() - 1);
			if (ran < mutationRate) {
				fenotipo[i] = randGen;
			}
		}
		Sort sort = new Sort();
		fenotipo = sort.ordenar(fenotipo);
		Solucion newFenotipo = fo.CalcularFuncionesObjetivo(instancia, fenotipo);
		return newFenotipo;
	}

	private static int getRandom(int[] gen) {
		int rnd = new Random().nextInt(gen.length);
		return gen[rnd];
	}

	private static int getRandom(int size) {
		int rnd = new Random().nextInt(size);
		return rnd;
	}

	public ArrayList<Integer> posibleGenes(Solucion solution, Instancia instancia) {
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

}
