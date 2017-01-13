package com.data.sponsor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ComprobantePagoSponsor;
import entidades.Contrato;
import entidades.Pago;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

public class DataPago {
	
	public void insertCuotas(Contrato c) throws Exception{
		PreparedStatement stmt = null;
		String query="INSERT INTO pagosContratos (FechaVenc, IdContrato, FechaPago) VALUES(?, ?, NULL);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			for(Pago p : c.getPagos()){
				stmt.setDate(1, new java.sql.Date(p.getFechaVenc().getTime()));
				stmt.setInt(2, c.getId());
				stmt.addBatch();
				}
			stmt.executeBatch();
			}
		catch(SQLException sqlex){
			throw new SQLException("Error al guardar las cuotas en la base de datos.");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar registrar las cuotas.");
		}
		finally{
				try{
				if(stmt!=null){
					stmt.close();
					}
				FactoryConnection.getInstancia().releaseConn();
				}
				catch(Exception ex){
				throw new Exception("Error no controlado al intentar cerrar la conexion.");
				}
			}
	}

	public Contrato getCuotas(Contrato c) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM pagosContratos WHERE IdContrato=?";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, c.getId());
			rs = stmt.executeQuery();
			while(rs.next()){
				Pago p = new Pago();
				p.setFechaPago(rs.getDate("FechaPago"));
				p.setFechaVenc(rs.getDate("FechaVenc"));
				c.getPagos().add(p);
			}
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar consultar la tabla de pagos.");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar consultar los pagos.");
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch(Exception ex){
				throw new Exception("Error no controlado al intentar cerrar la conexion.");
			}
		}
		return c;
	}
	
	public void updateCuotas(ComprobantePagoSponsor comprobante) throws Exception{
		PreparedStatement stmt = null;
		String query = "UPDATE pagosContratos SET FechaPago=now(), Importe=?, Recargo=?, Descuento=? WHERE FechaVenc=? AND IdContrato=?;";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			for(Pago p : comprobante.getCuotas()){
				stmt.setFloat(1, p.getImporteAbonado());
				stmt.setFloat(2, p.getRecargo());
				stmt.setFloat(3, p.getDescuento());
				stmt.setDate(4, new java.sql.Date(p.getFechaVenc().getTime()));
				stmt.setInt(5, comprobante.getContrato().getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la tabla de pagos.");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar registrar los pagos.");
		}
		finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch(Exception ex){
				throw new Exception("Error no controlado al intentar cerrar la conexion.");
			}
		}
	}
}
