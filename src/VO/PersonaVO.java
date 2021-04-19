package VO;

public class PersonaVO {
	private int idPersona;
	private int idCiudad;
	private String nombre;
	private char tipo;
	private boolean infectado;
	
	PersonaVO(int idPersona, int idCiudad, String nombre, char tipo, boolean infectado){
		this.idPersona = idPersona;
		this.idCiudad = idCiudad;
		this.nombre = nombre;
		this.tipo = tipo;
		this.infectado = infectado;
	}

	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the idCiudad
	 */
	public int getIdCiudad() {
		return idCiudad;
	}

	/**
	 * @param idCiudad the idCiudad to set
	 */
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the infectado
	 */
	public boolean isInfectado() {
		return infectado;
	}

	/**
	 * @param infectado the infectado to set
	 */
	public void setInfectado(boolean infectado) {
		this.infectado = infectado;
	}
}
