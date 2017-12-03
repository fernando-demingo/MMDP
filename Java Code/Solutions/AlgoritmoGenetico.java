package Solutions;

import java.util.ArrayList;
import java.util.Random;

import DataStructures.Instancia;
import DataStructures.Solucion;
import Functions.MinMax;
import Functions.Sort;

public class AlgoritmoGenetico {
	private int populationSize;
	private double mutationRate;
	private double corssoverRate;
	private int elitismCount;
	private Instancia instancia;
	private Alterar alterar;
	private MinMax minMax;
	private Sort sort;
	private int generations;
	private int time;
	int currentGeneration;
	private boolean print;

	public AlgoritmoGenetico(Instancia instancia, int generations, int time, int populationSize, double crossoverRate,
			double mutationRate, int elitismCount, boolean print) {
		this.instancia = instancia;
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.corssoverRate = crossoverRate;
		this.elitismCount = elitismCount;
		this.generations = generations;
		this.time = time;
		this.print = print;
		alterar = new Alterar();
		minMax = new MinMax();
		sort = new Sort();
	}

	public Solucion Iniciar() {
		ArrayList<Solucion> poblacion = initPopulation(instancia, populationSize);
		evaluateSolution(poblacion);
		Solucion best;
		currentGeneration = 0;
		long tStart = System.currentTimeMillis();
		while (true) {
			poblacion = crossoverPopulation(poblacion);
			poblacion = mutatePopulation(poblacion);
			best = evaluateSolution(poblacion);
			if (print == true) {
				System.out.println("Generacion: " + currentGeneration);
			}
			currentGeneration += 1;
			long tEnd = System.currentTimeMillis();
			int t = (int) calculateTime(tStart, tEnd);
			if (currentGeneration == generations || t == time) {
				break;
			}
		}
		return best;
	}

	public int getGeneration() {
		return currentGeneration;
	}

	public Solucion evaluateSolution(ArrayList<Solucion> poblacion) {
		Solucion bestSolucion = minMax.getMax(poblacion);
		if (print == true) {
			imprimirSolucion(bestSolucion);
		}
		return bestSolucion;
	}

	public ArrayList<Solucion> initPopulation(Instancia instancia, int populationSize) {
		SolucionAleatoria solucionAleatoria = new SolucionAleatoria();
		ArrayList<Solucion> poblacion = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			Solucion solAl = solucionAleatoria.generateSolucion(instancia, 10, 0);
			poblacion.add(solAl);
		}
		return poblacion;

	}

	public ArrayList<Solucion> crossoverPopulation(ArrayList<Solucion> poblacion) {
		poblacion = sort.ordenarIndividuos(poblacion);
		ArrayList<Solucion> crossoverPop = new ArrayList<Solucion>();
		for (int i = 0; i < elitismCount; i++) {
			crossoverPop.add(poblacion.get(i));
		}
		while (true) {

			Solucion parent1 = selectParent(poblacion);
			Solucion parent2 = selectParent(poblacion);

			double c = new Random().nextDouble();
			if (c < corssoverRate) {
				ArrayList<Solucion> newIndividuos = alterar.Cruce(instancia, parent1, parent2);
				crossoverPop.addAll(newIndividuos);

			}

			if (crossoverPop.size() >= populationSize) {
				break;
			}
		}

		return crossoverPop;

	}

	public Solucion selectParent(ArrayList<Solucion> poblacion) {
		double[][] rangos = roulette(poblacion);
		double rand = new Random().nextDouble();
		rand = rand * 100.0d;
		int index = 0;
		for (int i = 0; i < rangos.length; i++) {
			if (rand >= rangos[i][0] && rand < rangos[i][1]) {
				index = i;
			}

		}
		Solucion parent = poblacion.get(index);
		return parent;
	}

	public ArrayList<Solucion> mutatePopulation(ArrayList<Solucion> poblacion) {
		ArrayList<Solucion> individuos = new ArrayList<Solucion>();
		poblacion = sort.ordenarIndividuos(poblacion);
		for (int i = 0; i < elitismCount; i++) {
			individuos.add(poblacion.get(i));
		}
		for (int i = elitismCount; i < poblacion.size(); i++) {
			double rand = new Random().nextDouble();
			if (rand < mutationRate) {
				Solucion sol = poblacion.get(i);
				Solucion newSol = alterar.Mutar(instancia, sol, mutationRate);
				individuos.add(newSol);
			} else {
				individuos.add(poblacion.get(i));
			}
		}
		return individuos;
	}

	public double[][] roulette(ArrayList<Solucion> poblacion) {
		double suma = 0.0d;
		for (int i = 0; i < poblacion.size(); i++) {
			suma = suma + poblacion.get(i).getDistance();
		}
		double[][] rangos = new double[poblacion.size()][2];
		rangos[0][0] = 0.0d;
		rangos[0][1] = porcentaje(suma, poblacion.get(0).getDistance());

		for (int i = 1; i < poblacion.size(); i++) {
			rangos[i][0] = rangos[i - 1][1];
			rangos[i][1] = rangos[i][0] + porcentaje(suma, poblacion.get(i).getDistance());
		}

		return rangos;

	}

	public double porcentaje(double total, double value) {
		double p = (value * 100.0d) / total;
		return p;
	}

	public void sortPopulation(ArrayList<Solucion> poblacion) {
		ArrayList<Double> aux = new ArrayList<Double>();
		ArrayList<Solucion> poblacionOrdenada = new ArrayList<Solucion>();
		for (int i = 0; i < poblacion.size(); i++) {
			aux.add(poblacion.get(i).getDistance());

		}

	}

	public static double getDoubleRandom(int value) {
		value = value * 100;
		int rnd = new Random().nextInt(value);
		double r = ((double) rnd) / 100.0d;
		return r;
	}

	public static int getRandom(int value) {
		int rnd = new Random().nextInt(value);
		return rnd;
	}

	public static void imprimirSolucion(Solucion solucion) {
		System.out.print("Funcion objetivo= " + solucion.getDistance() + " con ");
		for (int i = 0; i < solucion.getSelected().length; i++) {
			System.out.print(solucion.getSelected()[i] + "|");
		}
		System.out.println("");
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

	}

	public static double calculateTime(long tStart, long tEnd) {
		long tDelta = tEnd - tStart;
		double seconds = tDelta / 1000.0;
		return seconds;
	}

}
