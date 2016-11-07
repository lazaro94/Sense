package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Contacto;
import entidades.Sponsor;

public class DataContacto {

	public void insert(Contacto c, Sponsor s) throws Exception{		
		PreparedStatement stmt = null;
		String query = "INSERT INTO contactos (Nombre, Apellido, Direccion, Mail, Telefono1, Telefono2, DNI, Cargo, IdSponsor) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getApellido());
			stmt.setString(3, c.getDireccion());
			stmt.setString(4, c.getMail());
			stmt.setString(5, c.getTelefono1());
			stmt.setString(6, c.getTelefono2());
			stmt.setString(7, c.getDni());
			stmt.setString(8, c.getCargo());
			stmt.setInt(8, s.getId());
			stmt.execute();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar guardar el contacto");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar guardar el contacto");
		}
		finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new SQLException("Error la intentar cerrar la conexion");
			}
			catch(Exception ex){
				throw new Exception ("Error no controlado al intentar cerrar las conexiones");
			}
		}
	}
}
