package Personas;

import PropiedadesPersonas.Infectable;
import PropiedadesPersonas.Moveable;

public class Paciente extends Persona implements Infectable, Moveable{

	public Paciente(Persona p) {
		super(p);
	}
	
	@Override
	/**Método con el infectaremos a un paciente*/
	public void infectar(boolean infectado) {
		this.infectado = infectado;
	}

	@Override
	public void visitarSuper() {
		int ale = (int) Math.floor(Math.random()*100+1);//Saca un número entre el 1 y el 100
		
		//Si es menor o igual al porcentaje
		if(ale <= POR_SUPER)
			infectar(true);
	}

	@Override
	public void visitarTrabajo() {
		int ale = (int)Math.floor(Math.random()*100+1);//Saca un número entre el 1 y el 100
		
		//Si es menor o igual al porcentaje
		if(ale <= POR_TRABAJO)
			infectar(true);
	}

	@Override
	public void visitarTransporte() {
		int ale = (int)Math.floor(Math.random()*100+1);//Saca un número entre el 1 y el 100
		
		//Si es menor o igual al porcentaje
		if(ale <= POR_TRANSPORTE)
			infectar(true);
		
	}
	
	

}
