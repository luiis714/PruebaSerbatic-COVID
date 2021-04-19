package Personas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import PropiedadesPersonas.Infectable;
import PropiedadesPersonas.Vacunable;

public class Enfermero extends Persona implements Vacunable{

	public Enfermero(Persona p) {
		super(p);
	}
	
	@Override
	public void vacunar(Paciente infectado) {
		//Cambio el infectar a false para que quede vacunado
		infectado.infectar(false);
	}

	@Override
	public void vacunar(ArrayList<Paciente> listaInfectados) {
		//Primera pasada solo vacuna a 2 de cada 5
		for(short i = 0, vacunados = 0; i < listaInfectados.size(); i++) {
			//Iteracion para agruparlos en 5
			for(short j = 0; j < 5 && i < listaInfectados.size(); j++, i++) {
				//Si en el grupo no han llegado a 2 se vacuna
				if(vacunados <= 2) {
					vacunar(listaInfectados.get(i));
					vacunados++;
				}	
			}
			vacunados = 0; //Pongo a 0 los vacunados para el siguiente grupo
		}
		
		//Segunda pasada solo vacuna a 1 de cada 5
		for(short i = 0, vacunados = 0; i < listaInfectados.size(); i++) {
			//Iteracion para agruparlos en 5
			for(short j = 0; j < 5 && i < listaInfectados.size(); j++, i++) {
				//Si en el grupo no han llegado a 2 se vacuna
				if(vacunados <= 1) {
					vacunar(listaInfectados.get(i));
					vacunados++;
				}	
			}
			vacunados = 0;//Pongo a 0 los vacunados para el siguiente grupo
		}
	}

}
