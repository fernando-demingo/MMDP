package Solutions;

import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.ConstruccionAleatoria;
import Functions.FuncionObjetivo;

public class SolucionAleatoria {
	boolean printResults;

	public SolucionAleatoria() {
		printResults = false;
	}

	public Solucion generateSolucion(Instancia instancia, int loop, int seed) {
		ConstruccionAleatoria random;
		if (seed != 0) {
			random = new ConstruccionAleatoria(seed);
		} else {
			random = new ConstruccionAleatoria((int) (Math.random() * 10000));
		}

		FuncionObjetivo fOb = new FuncionObjetivo();
		int[] selecMejor = random.select(instancia.getN(), instancia.getM());
		Solucion resultMejor = fOb.CalcularFuncionesObjetivo(instancia, selecMejor);
		if (printResults == true) {
			System.out.println("Se inicia con FO: " + resultMejor.getDistance());
			System.out.print("usando la seleccion:");
			for (int i = 0; i < resultMejor.getSelected().length; i++) {
				System.out.print(resultMejor.getSelected()[i] + "|");
			}
			System.out.print("\n");
		}

		for (int i = 0; i < loop; i++) {

			if (seed == 0) {
				random = new ConstruccionAleatoria((int) (Math.random() * 10000));
			}
			int[] selection = random.select(instancia.getN(), instancia.getM());
			Solucion result = fOb.CalcularFuncionesObjetivo(instancia, selection);
			if (result.getDistance() > resultMejor.getDistance()) {
				resultMejor = result;
				if (printResults == true) {
					System.out.println(i + ".- " + "Se ha encontrado una mejor Fo: " + resultMejor.getDistance());
					System.out.print("usando la seleccion:");
					for (int j = 0; j < resultMejor.getSelected().length; j++) {
						System.out.print(resultMejor.getSelected()[j] + "|");
					}
					System.out.print("\n");
				}
			}

		}
		return resultMejor;
	}

	public boolean isPrintResults() {
		return printResults;
	}

	public void setPrintResults(boolean printResults) {
		this.printResults = printResults;
	}

}
