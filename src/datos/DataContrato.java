package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Contrato;

public class DataContrato {
	
	public ArrayList<Contrato> contratos() throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT FechaInicio, FechaFin, DiaPago, Monto, Comentario, IdContrato, Codigo, IdSponsor, Descripcion FROM contratos;";
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
	
	public void insertContrato(Contrato c) throws Exception{
		PreparedStatement stmt = null;
		String query = "INSERT INTO contratos (idSponsor, FechaInicio, fechaFin, diaPago, Monto, Codigo, Descripcion) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, c.getSponsor().getId());
			stmt.setDate(2, new java.sql.Date(c.getFechaFin().getTime()));
			stmt.setDate(3, new java.sql.Date(c.getFechaFin().getTime()));
			stmt.setInt(4, c.getDiaPago());
			stmt.setDouble(5, c.getMonto());
			stmt.setString(6, c.getCodigo());
			stmt.setString(7, c.getDescripcion());
			
			stmt.execute();
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
	}
	
	public void update(Contrato c) throws Exception{
		PreparedStatement stmt;
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
	}

}
