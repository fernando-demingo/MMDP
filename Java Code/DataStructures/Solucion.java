package DataStructures;

public class Solucion {
	protected int[] selected;
	protected double distance;
	protected int[] min;

	public int[] getMin() {
		return min;
	}

	public void setMin(int[] min) {
		this.min = min;
	}

	public Solucion(int[] selected, int[] min, double distance) {
		this.distance = distance;
		this.selected = selected;
		this.min = min;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
