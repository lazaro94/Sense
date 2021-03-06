package com.data.sponsor;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import entidades.Contacto;

public class DataContacto {

	public void insert(Contacto c) throws Exception{		
		PreparedStatement stmt = null;
		String query = "INSERT INTO contactos (Nombre, Apellido, Direccion, Mail, Telefono1, Telefono2, DNI, Cargo, IdSponsor, FechAlta) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, now());";
		
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
			stmt.setInt(9, c.getSponsor().getId());
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
	
	public void update(Contacto c) throws Exception{
		PreparedStatement stmt = null;
		String query = "UPDATE contactos SET Nombre=?, Apellido=?, Direccion=?, Mail=?, Telefono1=?, Telefono2=?, DNI=?, Cargo=? WHERE IdContacto=?";
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
			stmt.setInt(9, c.getIdContacto());
			
			stmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la tabla contactos");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar actualizar el contacto");
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
	
	public void delete(Contacto c) throws Exception{
		PreparedStatement stmt = null;
		String query = "DELETE FROM contactos WHERE IdContacto=?";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, c.getIdContacto());
			stmt.execute();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar eliminar el contacto de la tabla");
		}
		catch(Exception ex){
			throw new SQLException("Error no controlado al intentar borrar el contacto");
		}
		finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch(Exception ex){
				throw new Exception("Error no controlado al intentar cerrar la conexi�n.");
			}
		}
	}
}
