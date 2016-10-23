package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Contrato;
import entidades.Sponsor;

public class DataContrato {
	
	public ArrayList<Contrato> contratos() throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM contratos;";
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()){
				Contrato c = new Contrato(rs.getDate("FechaInicio"), rs.getDate("FechaFin"), rs.getInt("DiaPago"), rs.getFloat("Monto"), rs.getString("Comentario"), rs.getString("Codigo"), rs.getInt("IdContrato"), rs.getInt("idSponsor"));
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
	
	public void insertPublicidad(Sponsor s, Date fechaIni, Date fechaFin, int diaPago, Double monto, String codigo) throws Exception{
		PreparedStatement stmt = null;
		String query = "INSERT INTO contratos (idSponsor, FechaInicio, fechaFin, diaPago, Monto, Codigo) VALUES (?, ?, ?, ?, ?, ?);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setDate(2, fechaIni);
			stmt.setDate(3, fechaFin);
			stmt.setInt(4, diaPago);
			stmt.setDouble(5, monto);
			stmt.setString(6, codigo);
			
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

}
