package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import DataStructures.BaseLines;
import DataStructures.Data;
import DataStructures.Instancia;

public class LeerEscribirTxt {
	ArrayList<Data> data;
	float matrix[][];
	Instancia instance;

	public Instancia Leer(String path) {
		data = new ArrayList<Data>();
		String texto = "";
		FileReader fr = null;
		try {
			File archivo = new File(path);
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			//// read the first line
			String linea;
			linea = br.readLine();
			String mn[] = linea.split(" ");

			while ((linea = br.readLine()) != null) {
				String t[] = linea.split(" ");
				Data d = new Data(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Float.parseFloat(t[2]));
				data.add(d);
			}
			int m = Integer.parseInt(mn[0]);
			int n = Integer.parseInt(mn[1]);
			matrix = new float[m][m];
			for (int i = 0; i < data.size(); i++) {
				matrix[data.get(i).getX()][data.get(i).getY()] = data.get(i).getValue();
			}
			for (int i = 0; i < data.size(); i++) {
				matrix[data.get(i).getY()][data.get(i).getX()] = data.get(i).getValue();
			}

			instance = new Instancia(m, n, matrix);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return instance;
	}

	public ArrayList<BaseLines> LeerBaseLines(String path) {
		ArrayList<BaseLines> data = new ArrayList<BaseLines>();
		FileReader fr = null;
		try {
			File archivo = new File(path);
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			//// read the first line
			String linea;

			while ((linea = br.readLine()) != null) {
				String t[] = linea.split("\t");
				if (t.length == 2) {
					BaseLines d = new BaseLines(t[0], Float.parseFloat(t[1]));
					data.add(d);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return data;
	}

	public void escribir(String path, String txt) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(path, "UTF-8");
			writer.print(txt);
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
