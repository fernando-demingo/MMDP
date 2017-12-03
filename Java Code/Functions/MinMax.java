package Functions;

import java.util.ArrayList;

import DataStructures.Solucion;

public class MinMax {

	public Solucion getMax(ArrayList<Solucion> results) {
		int index = 0;
		double max = 0.0d;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getDistance() >= max) {
				max = results.get(i).getDistance();
				index = i;
			}
		}
		return results.get(index);
	}

	public Solucion getMin(ArrayList<Solucion> results) {
		int index = 0;
		double min = getMax(results).getDistance();
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getDistance() <= min) {
				min = results.get(i).getDistance();
				index = i;
			}
		}
		return results.get(index);

	}

}
