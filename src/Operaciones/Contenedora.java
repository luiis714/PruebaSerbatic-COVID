package Operaciones;


import java.sql.SQLException;
import java.util.ArrayList;

import BBDD.OperacionesBBDD;
import Personas.Enfermero;
import Personas.Paciente;
import Personas.Persona;
import PropiedadesPersonas.Infectable;

public class Contenedora {
	private static final char PACIENTE = 'P';
	private static final char ENFERMERO = 'E';
	
	public static ArrayList<Persona> listaGeneral = new ArrayList<>(); 
	public static ArrayList<Enfermero> listaEnfermeros = new ArrayList<>();
	public static ArrayList<Paciente> listaPacientes = new ArrayList<>();
	public static ArrayList<Paciente> listaInfectados = new ArrayList<>();
	
	public static void generaColecciones(int idCiudad) {
		
		try {
			//Saco la lista con una consulta a la BBDD
			listaGeneral = (ArrayList<Persona>) OperacionesBBDD.consultarPersonasCiudad(idCiudad);
		} catch (SQLException e) {
			System.out.println("Error al recuperar la lista");
		}
		
		//Agrupo en dos listas de PACIENTES y ENFERMEROS
		for(short i = 0; i < listaGeneral.size(); i++) {
			if(listaGeneral.get(i).getTipo() == PACIENTE) {
				Paciente pac = new Paciente(listaGeneral.get(i));
				
				listaPacientes.add(pac);
			}
			else if(listaGeneral.get(i).getTipo() == ENFERMERO) {
				Enfermero enf = new Enfermero(listaGeneral.get(i));
				
				listaEnfermeros.add(enf);
			}
		}
		//Informo al usuario
		System.out.println("Listas generadas con exito");
		
		/*//PRUEBA QUE MUESTRA LAS DOS LISTAS
		System.out.println("PACIENTES\n");
		for(short i = 0; i < listaPacientes.size(); i++) 
			System.out.println("id: " + listaPacientes.get(i).getIdPersona() + " - idCiudad: " + listaPacientes.get(i).getIdCiudad() + " - nombre: " + listaPacientes.get(i).getNombre() + " - tipo: " + listaPacientes.get(i).getTipo() + " - infectado: " + listaPacientes.get(i).isInfectado());
			
		
		System.out.println("\n\nENFERMEROS\n");
		
		for(short i = 0; i < listaEnfermeros.size(); i++) 
			System.out.println("id: " + listaEnfermeros.get(i).getIdPersona() + " - idCiudad: " + listaEnfermeros.get(i).getIdCiudad() + " - nombre: " + listaEnfermeros.get(i).getNombre() + " - tipo: " + listaEnfermeros.get(i).getTipo() + " - infectado: " + listaEnfermeros.get(i).isInfectado());
			
		*/
	}
	
	public static void simularDia() {
		//Recorro los pacientes para ver si se infectan
		for(short i = 0; i < listaPacientes.size(); i++) {
			/*Ale se usuará para ver que hacen los pacientes
			 * Si es 1 entonces visitanSuper
			 * Si es 2 entonces visitanTrabajo
			 * Si es 3 entonces visitanTransporte
			 * Si es 4 entonces visitanTodo
			 * */
			int ale = (int)Math.floor(Math.random()*4+1);
			//Indicaremos que hará el paciente
			switch(ale) {
				case 1:
					listaPacientes.get(i).visitarSuper();
					
					break;
				case 2:
					listaPacientes.get(i).visitarTrabajo();
					
					break;
				case 3:
					listaPacientes.get(i).visitarTransporte();
					
					break;
				case 4:
					listaPacientes.get(i).visitarSuper();
					
					//Si no está infectado sigue visitando sitios
					if(!listaPacientes.get(i).isInfectado())
						listaPacientes.get(i).visitarTrabajo();
					
					if(!listaPacientes.get(i).isInfectado())
						listaPacientes.get(i).visitarTransporte();
					
					break;
					
			}
		}
		//Hago un filtrado con una lista de solo infectados
		for(short i = 0; i < listaPacientes.size(); i++) 
			if(listaPacientes.get(i).isInfectado())
				listaInfectados.add(listaPacientes.get(i));
	
		/*//PRUEBA QUE MUESTRA INFECTADOS
		System.out.println("\n\nPACIENTES INFECTADOS\n");
		for(short i = 0; i < listaInfectados.size(); i++)
			System.out.println("id: " + listaInfectados.get(i).getIdPersona() + " - idCiudad: " + listaInfectados.get(i).getIdCiudad() + " - nombre: " + listaInfectados.get(i).getNombre() + " - tipo: " + listaInfectados.get(i).getTipo() + " - infectado: " + listaInfectados.get(i).isInfectado());
		*/
		//Se recupera el último enfermero añadido a la lista
		Enfermero enfermero;
		if(listaEnfermeros.size() != 0) {
			enfermero = listaEnfermeros.get(listaEnfermeros.size()-1);
			//El enfermero vacuna a los infectados
			enfermero.vacunar(listaInfectados);
		}
		
		/*//PRUEBA QUE MUESTRA INFECTADOS
				System.out.println("\n\nPACIENTES INFECTADOS\n");
				for(short i = 0; i < listaPacientes.size(); i++) 
					if(listaPacientes.get(i).isInfectado())
						System.out.println("id: " + listaPacientes.get(i).getIdPersona() + " - idCiudad: " + listaPacientes.get(i).getIdCiudad() + " - nombre: " + listaPacientes.get(i).getNombre() + " - tipo: " + listaPacientes.get(i).getTipo() + " - infectado: " + listaPacientes.get(i).isInfectado());
		*/
	}
}
