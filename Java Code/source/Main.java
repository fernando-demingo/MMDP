package source;

import java.util.ArrayList;

import DataStructures.BaseLines;
import DataStructures.Instancia;
import DataStructures.Solucion;
import File.LeerEscribirTxt;
import Solutions.AlgoritmoGenetico;
import Solutions.SolucionAleatoria;

public class Main {
	static SolucionAleatoria solucionAleatoria;

	public static void main(String[] args) {

		LeerEscribirTxt leer = new LeerEscribirTxt();
		ArrayList<BaseLines> baseLines = new ArrayList<BaseLines>();
		String path = "D://Instancias-GKD-Ic//";
		baseLines = leer.LeerBaseLines(path + "baseline.txt");
		String texto = "";
		for (int i = 0; i < baseLines.size(); i++) {
			Instancia instancia = leer.Leer(path + baseLines.get(i).getName());
			AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(instancia, 100000, 60, 100, 0.7, 0.1, 2, false);
			Solucion solucion = algoritmoGenetico.Iniciar();
			int gen = algoritmoGenetico.getGeneration();
			System.out.println("Generation " + gen + "--" + baseLines.get(i).getName() + "--" + solucion.getDistance());
			texto = texto + baseLines.get(i).getName() + "\t" + solucion.getDistance() + "\t" + "Generation " + gen
					+ "\n";

		}
		leer.escribir(path + "resultsAG-4.txt", texto);

	}

	public static String calculateTime(long tStart, long tEnd) {
		long tDelta = tEnd - tStart;
		double seconds = tDelta / 1000.0;
		int h = (int) Math.floor((seconds / 3600.0));
		seconds = seconds - (3600.0 * h);
		int m = (int) Math.floor(seconds / 60.0);
		seconds = seconds - (m * 60.0);
		int s = (int) seconds;

		String ht;
		if (h < 10) {
			ht = "0" + Integer.toString(h);
		} else {
			ht = Integer.toString(h);
		}
		String mt;
		if (m < 10) {
			mt = "0" + Integer.toString(m);
		} else {
			mt = Integer.toString(m);
		}
		String st;
		if (s < 10) {
			st = "0" + Integer.toString(s);
		} else {
			st = Integer.toString(s);
		}

		String eTime = ht + ":" + mt + ":" + st;
		return eTime;
	}

	public static void imprimirSolucion(Solucion solucion) {
		System.out.print("Funcion objetivo= " + solucion.getDistance() + " con ");
		for (int i = 0; i < solucion.getSelected().length; i++) {
			System.out.print(solucion.getSelected()[i] + "|");
		}
		System.out.println("");
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

	}

}
