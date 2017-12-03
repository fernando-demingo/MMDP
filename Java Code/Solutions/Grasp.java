package Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import Busqueda.FirstImprovement;
import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.MinMax;

public class Grasp {

	public Solucion solucionGrasp(Instancia instancia, float baseLine) {
		ArrayList<Solucion> RCL = ConstruccionAleatoriaMiope(instancia, baseLine);
		ArrayList<Solucion> solucionesFinales = new ArrayList<Solucion>();
		FirstImprovement improvement = new FirstImprovement(instancia);
		int size = 0;
		if (RCL.size() >= 6) {
			size = 5;
		} else {
			size = RCL.size();
		}
		for (int i = 0; i < size; i++) {
			System.out.print(".");
			int s = getRandom(RCL.size());
			Solucion solAux = RCL.get(s);
			RCL.remove(s);
			Solucion solMejorada = improvement.mejorar(solAux);
			solucionesFinales.add(solMejorada);
		}
		System.out.println("");
		MinMax minMax = new MinMax();
		Solucion bestSolution;
		if (solucionesFinales.size() > 0) {
			bestSolution = minMax.getMax(solucionesFinales);
		} else {
			bestSolution = null;
		}
		return bestSolution;

	}

	public ArrayList<Solucion> ConstruccionAleatoriaMiope(Instancia instancia, float baseLine) {
		ArrayList<Solucion> L = new ArrayList<Solucion>();
		ArrayList<Solucion> RCL = new ArrayList<Solucion>();
		SolucionAleatoria solucionAleatoria = new SolucionAleatoria();
		SolucionVoraz solVoraz = new SolucionVoraz();

		for (int i = 0; i < 50; i++) {
			Solucion posibSol = solVoraz.generateSolution4(instancia, false);
			L.add(posibSol);
		}
		for (int i = 0; i < L.size(); i++) {
			if (L.get(i).getDistance() >= baseLine) {
				RCL.add(L.get(i));
			}
		}
		return RCL;
	}

	public ArrayList<Solucion> ordenarArraySoluciones(ArrayList<Solucion> soluciones) {
		ArrayList<Solucion> sol = soluciones;
		Collections.sort(sol, new Comparator<Solucion>() {
			@Override
			public int compare(Solucion o1, Solucion o2) {
				int v = 0;
				double f1 = o1.getDistance();
				double f2 = o2.getDistance();
				if (f1 > f2) {
					v = 1;
				}
				if (f1 < f2) {
					v = -1;
				}
				if (f1 == f2) {
					v = 0;
				}
				return v;
			}
		});
		return sol;
	}

	public static void imprimirSolucion(Solucion solucion) {
		System.out.print("Funcion objetivo= " + solucion.getDistance() + " con ");
		for (int i = 0; i < solucion.getSelected().length; i++) {
			System.out.print(solucion.getSelected()[i] + "|");
		}
		System.out.println("");

	}

	public static int getRandom(int num) {
		int rnd = new Random().nextInt(num);
		return rnd;
	}
}
