package Main;
import java.net.URL;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import BBDD.HiloInsertarBBDD;
import BBDD.OperacionesBBDD;
import Operaciones.Contenedora;

public class Principal {

	public static Logger log = LogManager.getLogger(Principal.class);
	
	public static void main(String[] args) {
		//Configuración del logger
		String url = "C:\\Users\\Formacion\\eclipse2021-workspace\\MangasRuiz_Luis_PruebaSerbatic\\src\\resources\\log4j.properties";
		PropertyConfigurator.configure(url);
		
		Scanner teclado = new Scanner(System.in);
		
		//El usuario inserta por teclado el ID de la ciudad
		System.out.print("Inserte el número de la ciudad: ");
		int idCiudad = teclado.nextInt(); 
		
		log.info("Ciudad sacada correctamente. Ciudad: " + idCiudad);
		
		teclado.close();
		
		//Genero las colecciones con las que se van a interactuar en el programa
		Contenedora.generaColecciones(idCiudad);
		log.info("Colecciones generadas");
		
		Contenedora.simularDia();
		log.info("Día simulado correctamente");
		
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
			log.error("Error en la espera del los hilos");
		}
		
		System.out.println("\nTotal de pacientes infectados: " + OperacionesBBDD.numPacientes());
	}

}
