package com.company;

import java.io.*;
import java.util.ArrayList;

public class Archivo<T> {
	private String archivoBase;
	private ArrayList<T> base;

	public Archivo(String filePATH){
		base = new ArrayList<>();
		this.archivoBase = filePATH;
	}

	public ArrayList<T> recuperar() {

		File archivo = new File(archivoBase);
		if (archivo.exists()) {
			try {
				FileInputStream flujoEntrada = new FileInputStream(archivo);
				ObjectInputStream entradaObjeto = new ObjectInputStream(flujoEntrada);

				boolean finArchivo = false;

				while (true) {
					try {
						T leido = (T) entradaObjeto.readObject();
						base.add(leido);
					}
					catch(EOFException excep){
						break;
					}
				}
				flujoEntrada.close();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("No se puede leer la base de datos: " + e.getMessage());
				e.printStackTrace();
			} finally {
				//************************* NO ME DEJA CERRAR EL STREAM. *****************************
			}
		}

		return base;
	}

	public void persistir(ArrayList<T> base) {

		File archivo = new File(archivoBase);

		try {
			if (!archivo.exists()) {
				if (archivo.createNewFile()) {

					FileOutputStream flujoSalida = new FileOutputStream(archivo);
					ObjectOutputStream salidaObjeto = new ObjectOutputStream(flujoSalida);

					for (T aux : base) {
						salidaObjeto.writeObject(aux);
					}
					salidaObjeto.close();
					flujoSalida.close();
				}
			}
		} catch(IOException e){
			System.out.println("No se puede leer la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
