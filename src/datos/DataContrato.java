package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.Sponsor;

public class DataContrato {
	
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
