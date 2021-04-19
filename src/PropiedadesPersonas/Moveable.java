package PropiedadesPersonas;

public interface Moveable {
	
	static final int POR_SUPER = 10;
	static final int POR_TRABAJO = 12;
	static final int POR_TRANSPORTE = 16;
	
	public void visitarSuper();
	public void visitarTrabajo();
	public void visitarTransporte();
}
