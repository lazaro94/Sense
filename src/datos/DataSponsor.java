package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Sponsor;
import util.AppException;

public class DataSponsor {
	
	//Patron Singleton//
	
	/*private static DataSponsor _instancia;
	
	public static DataSponsor getInstancia(){
		if (_instancia==null){
			_instancia = new DataSponsor();
		}
		return _instancia;
	}*/
	
	public ArrayList<Sponsor> getSponsors() throws Exception{
		
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		PreparedStatement stmt = null;
		ResultSet rs=null;
		String query = "Select RazonSocial, Direccion, Cuit, IdSponsors, Numero FROM Sponsors;";
		
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			rs=stmt.executeQuery();
			while (rs.next()){
				Sponsor s = new Sponsor(rs.getInt("IdSponsors"),rs.getString("RazonSocial"), rs.getString("Cuit"), rs.getString("Direccion"), rs.getString("Numero"));
				sponsors.add(s);
			}
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar consultar la tabla Sponsors");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al consultar los datos de Sponsors");
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
			catch(SQLException sqlex){
				throw new SQLException("Error al intentar cerrar la conexion");
			}
			catch(Exception ex){
				throw new Exception("Error no controlado al intentar cerrar conexiones");
			}

		}
		return sponsors;		
	}
	
	public void update(Sponsor s) throws Exception{
		PreparedStatement stmt=null;
		String query="UPDATE Sponsors SET Cuit=?, RazonSocial=?, Direccion=?, Numero=? " + "WHERE IdSponsors=?";
		
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, s.getCuit());
			stmt.setString(2, s.getRazonSocial());
			stmt.setString(3, s.getCalle());
			stmt.setString(4, s.getNumero());
			stmt.setInt(5, s.getId());
			stmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la tabla Sponsors");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar actualizar la tabla Sponsors");
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
	
	public void insert(Sponsor s) throws Exception{
		PreparedStatement stmt = null;
		String query = "INSERT INTO Sponsors (Cuit, RazonSocial, Direccion, Numero) VALUES (?, ?, ?, ?)";
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, s.getCuit());
			stmt.setString(2, s.getRazonSocial());
			stmt.setString(3, s.getCalle());
			stmt.setString(4, s.getNumero());
			stmt.execute();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar ingresar el sponsor");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar agregar el sponsor");
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
	
	public void delete(Sponsor s) throws Exception{
		PreparedStatement stmt= null;
		String query = "DELETE FROM Sponsors WHERE IdSponsors=?";
		
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.execute();
		}
		catch(SQLException sqlex){
			throw new SQLException("Eror al intentar eliminar el sponsor");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar eliminar el sponsor");
		}
		finally{
			try{
				if (stmt!=null){
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
	
	public Sponsor getIdByRS(Sponsor s) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "SELECT IdSponsors FROM Sponsors WHERE RazonSocial=?";
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, s.getRazonSocial());
			rs=stmt.executeQuery();
			if (rs.next()){
				s.setId(rs.getInt("IdSponsors"));
			}
			else{
				throw new AppException("Sponsor no encontrado");
			}
		}
		catch(AppException appex){
				throw appex;
			}
		catch(SQLException sqlex){
			throw new SQLException("Error al consultar la tabla Sponsors");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al consultar la tabla Sponsors");
		}
		finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				if(rs!=null){
					rs.close();
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
		return s;
	}
	
	public void insertPublicidad(Sponsor s, Date fechaIni, Date fechaFin, int diaPago, Double monto) throws Exception{
		PreparedStatement stmt = null;
		String query = "INSERT INTO Publicidades (idSponsor, FechaInicio, fechaFin, diaPago, Monto) VALUES (?, ?, ?, ?, ?);";
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setDate(2, fechaIni);
			stmt.setDate(3, fechaFin);
			stmt.setInt(4, diaPago);
			stmt.setDouble(5, monto);
			
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
