package com.data.sponsor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Contrato;
import util.AppException;

public class DataContrato {
	
	public ArrayList<Contrato> contratos() throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT FechaInicio, FechaFin, DiaPago, Monto, Comentario, IdContrato, Codigo, IdSponsor, Descripcion FROM contratos WHERE FechaBaja IS NULL;";
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()){
				Contrato c = new Contrato(rs.getTimestamp("FechaInicio"), rs.getTimestamp("FechaFin"), rs.getInt("DiaPago"), rs.getFloat("Monto"), rs.getString("Comentario"), rs.getString("Codigo"), rs.getInt("IdContrato"), rs.getInt("IdSponsor"), rs.getString("Descripcion"));
				contratos.add(c);
			}
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar consultar la tabla de contratos");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar obtener datos de los contratos");
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
		return contratos;
	}
	
	public Contrato insertContrato(Contrato c) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "INSERT INTO contratos (idSponsor, FechaInicio, fechaFin, diaPago, Monto, Codigo, Descripcion) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, c.getSponsor().getId());
			stmt.setDate(2, new java.sql.Date(c.getFechaInicio().getTime()));
			stmt.setDate(3, new java.sql.Date(c.getFechaFin().getTime()));
			stmt.setInt(4, c.getDiaPago());
			stmt.setDouble(5, c.getMonto());
			stmt.setString(6, c.getCodigo());
			stmt.setString(7, c.getDescripcion());			
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			if(rs.next()){
				c.setId(rs.getInt(1));
			}else{
				throw new AppException("No se pudieron recuperar los datos del contrato ingresado.");
			}
					
		}
		catch(AppException appex){
			throw appex;
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar registrar la publicidad");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar registrar la publicidad");
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
		return c;
	}
	
	public void update(Contrato c) throws Exception{
		PreparedStatement stmt=null;
		String query = "UPDATE contratos SET IdSponsor=?, FechaInicio=?, FechaFin=?, DiaPago=?, Monto=?, Comentario=?, Codigo=?, Descripcion=? WHERE IdContrato=?";
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, c.getSponsor().getId());
			stmt.setDate(2, new java.sql.Date(c.getFechaFin().getTime()));
			stmt.setDate(3, new java.sql.Date(c.getFechaFin().getTime()));
			stmt.setInt(4, c.getDiaPago());
			stmt.setDouble(5, c.getMonto());
			stmt.setString(6, c.getComentario());
			stmt.setString(7, c.getCodigo());
			stmt.setString(8, c.getDescripcion());
			stmt.setInt(9, c.getId());
			stmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la tabla contratos");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar actualizar el contrato");
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

	public void anular(Contrato c) throws Exception{
		PreparedStatement stmt=null;
		String query = "UPDATE contratos SET FechaBaja = now() WHERE IdContrato=?";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la baja del Contrato.");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar dar de baja el Contrato.");
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
