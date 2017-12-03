package DataStructures;

public class Instancia {

	private int M;
	private int N;
	private float[][] instanceMatrix;

	public Instancia(int M, int N, float[][] instanceMatrix) {
		this.M = M;
		this.N = N;
		this.instanceMatrix = instanceMatrix;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public float[][] getInstanceMatrix() {
		return instanceMatrix;
	}

	public void setInstanceMatrix(float[][] instanceMatrix) {
		this.instanceMatrix = instanceMatrix;
	}

	public float getValueAt(int x, int y) {
		return instanceMatrix[x][y];
	}

}
