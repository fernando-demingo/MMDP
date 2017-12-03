package Solutions;

import DataStructures.Solucion;

public class Individuo {
	Solucion solucion;
	boolean cruce;

	public Individuo(Solucion solucion, boolean cruce) {
		this.solucion = solucion;
		this.cruce = cruce;
	}

	public Solucion getSolucion() {
		return solucion;
	}

	public void setSolucion(Solucion solucion) {
		this.solucion = solucion;
	}

	public boolean isCruce() {
		return cruce;
	}

	public void setCruce(boolean cruce) {
		this.cruce = cruce;
	}

}
