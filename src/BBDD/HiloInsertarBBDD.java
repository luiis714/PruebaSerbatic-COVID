package BBDD;

import java.util.ArrayList;

import Operaciones.Contenedora;
import Personas.Paciente;

public class HiloInsertarBBDD extends Thread {
	
	int inicio, fin;
	
	public HiloInsertarBBDD(int inicio, int fin) {
		//Para indicar DESDE donde HASTA donde se quiere insertar
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run() {
		//Inserto todos los pacientes
		for(int i = inicio; i < fin; i++)
			OperacionesBBDD.insertarPersona(Contenedora.listaPacientes.get(i));
		
		//Informa al usuario
		System.out.println("Inserción correcta.");
	}
}
