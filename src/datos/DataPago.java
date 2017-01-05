package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Contrato;
import entidades.Pago;

public class DataPago {

	public Contrato getCuotas(Contrato c) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM pago WHERE IdContrato=?";
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
}
