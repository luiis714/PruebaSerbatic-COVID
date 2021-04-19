package BBDD;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Main.Principal;
import Operaciones.Contenedora;
import Personas.Paciente;
import jdk.internal.org.jline.utils.Log;

public class HiloInsertarBBDD extends Thread {
	
	int inicio, fin;
	
	private static Logger log = LogManager.getLogger(Principal.class);
	
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
		log.info("Inserción correcta");
	}
}
