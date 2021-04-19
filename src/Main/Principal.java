package Main;
import java.util.Scanner;

import BBDD.HiloInsertarBBDD;
import BBDD.OperacionesBBDD;
import Operaciones.Contenedora;

public class Principal {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		//El usuario inserta por teclado el ID de la ciudad
		System.out.print("Inserte el número de la ciudad: ");
		int idCiudad = teclado.nextInt(); 
		
		teclado.close();
		
		//Genero las colecciones con las que se van a interactuar en el programa
		Contenedora.generaColecciones(idCiudad);

		Contenedora.simularDia();
		
		//Indico de cuanto serán los intervalos dividiendo entre los hilos
		int inter = (Contenedora.listaPacientes.size()/4);
		
		//Creo los hilos
		HiloInsertarBBDD h1 = new HiloInsertarBBDD(0, inter);
		HiloInsertarBBDD h2 = new HiloInsertarBBDD(inter, (inter*2));
		HiloInsertarBBDD h3 = new HiloInsertarBBDD((inter*2), (inter*3));
		HiloInsertarBBDD h4 = new HiloInsertarBBDD((inter*3), Contenedora.listaPacientes.size());
		//Y los inicio
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTotal de pacientes infectados: " + OperacionesBBDD.numPacientes());
	}

}
