package PropiedadesPersonas;
import java.util.ArrayList;
import java.util.Collection;

import Personas.Paciente;

public interface Vacunable {
	
	public void vacunar(Paciente infectado);
	public void vacunar(ArrayList<Paciente> listaInfectados);
}
