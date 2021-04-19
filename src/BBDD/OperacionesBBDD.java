package BBDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Main.Principal;
import Personas.Persona;

public class OperacionesBBDD {
	
	private static Connection conexion = Conexion.getConexion();
    private static Statement st;
    
    private static Logger log = LogManager.getLogger(Principal.class);
    
    public OperacionesBBDD() {
    	this.conexion = Conexion.getConexion();
    	
    	if(conexion != null) {
    		try {
				st = conexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
	
    /**Metodo que inserta una persona en la tabla personas_informe*/
    public synchronized static void insertarPersona(Persona p) {
            PreparedStatement ps = null;
			try {
				ps = conexion.prepareStatement("INSERT INTO personas_informe (id, id_ciudad, Nombre, Tipo, Infectado) VALUES (?, ?, ?, ?, ?)");
				
				//Inserto la persona
				ps.setInt(1, p.getIdPersona());
	            ps.setInt(2, p.getIdCiudad());
	            ps.setString(3, p.getNombre());
	            ps.setString(4, Character.toString(p.getTipo()));
	            ps.setBoolean(5, p.isInfectado());
	            
	            int resultado = ps.executeUpdate();
	            
	            if (resultado == 0) {
	                System.out.println("NO se ha podido insertar");
	            }
	            
	            conexion.commit();
	            
	            ps.close();
			} catch (SQLException e) {
				System.out.println("Error en la inserción. " + e.getMessage());
				log.error("Error en la inserción. " + e.getMessage());
			}
    }
    
    /**Método que devuelve una coleccion de Personas de una ciudad especifica*/
	public static Collection<Persona> consultarPersonasCiudad(int idCiudad) throws SQLException {
	    String query = "SELECT * FROM personas WHERE id_ciudad=?";
	    PreparedStatement ps = conexion.prepareStatement(query);
	    ps.setInt(1, idCiudad);
	
	    ResultSet rs = ps.executeQuery();
	    
	    //Colllection de se devolverá
	    Collection<Persona> personasCiudad = new ArrayList<>();
	    
	    // Columnas de la tabla: nombre, apellidos y telefono
	    while (rs.next()) {
	    	Persona p = new Persona();
	    	
	    	p.setIdPersona(rs.getInt("id"));
	    	p.setIdCiudad(idCiudad);
	    	p.setNombre(rs.getString("Nombre"));
	    	p.setTipo(rs.getString("Tipo").charAt(0));//Saca solo el primer char
	    	p.setInfectado(rs.getBoolean("Infectado"));
	    	
	    	personasCiudad.add(p);
	    }
	    
	    rs.close();
	    ps.close();
	    
	    return personasCiudad;
	}
	
	/**Método que a través de la funcion get_num_informe devuelve el número de pacientes infectados*/
	public static int numPacientes() {
		int total = -1;
		
		try {
            st = conexion.createStatement();

            if (conexion != null) {
                //** Operaciones
                //procedimiento almacenado sin parametros
                CallableStatement stmt = conexion.prepareCall("{?=call get_num_pacientes()}");
                
                stmt.registerOutParameter(1, Types.INTEGER);
                
                stmt.execute();
                //Recupero el valor devuelto
                total = stmt.getInt(1);

                Conexion.desconectar();
            } else {
                System.out.println("Conexion no realizada");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
		return total;
	}
}
