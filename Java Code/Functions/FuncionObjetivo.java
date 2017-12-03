package Functions;

import DataStructures.Instancia;
import DataStructures.Solucion;

public class FuncionObjetivo {

	public Solucion CalcularFuncionesObjetivo(Instancia instancia, int[] solution) {

		GenerateCombinations genCombinations = new GenerateCombinations();
		int[][] combinations = genCombinations.generate(solution);
		double distancia = 0.0d;
		double distFO = instancia.getValueAt(combinations[0][0], combinations[0][1]);
		int[] min = new int[2];
		min[0] = combinations[0][0];
		min[1] = combinations[0][1];
		for (int i = 0; i < combinations.length; i++) {
			int x = combinations[i][0];
			int y = combinations[i][1];

			distancia = instancia.getValueAt(x, y);
			// System.out.println(x + " y " + y + " dan " + distancia);

			if (distancia < distFO) {
				distFO = distancia;
				min[0] = x;
				min[1] = y;
			}

		}

		Solucion result = new Solucion(solution, min, distFO);
		return result;

	}

}
