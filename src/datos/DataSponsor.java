package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Sponsor;

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
		String query = "Select RazonSocial, Direccion, Cuit, IdSponsors FROM Sponsors;";
		
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			rs=stmt.executeQuery();
			while (rs.next()){
				Sponsor s = new Sponsor(rs.getInt("IdSponsors"),rs.getString("RazonSocial"), rs.getString("Cuit"), rs.getString("Direccion"));
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
		String query="UPDATE Sponsors SET Cuit=?, RazonSocial=?, Direccion=? " + "WHERE IdSponsors=?";
		
		try{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, s.getCuit());
			stmt.setString(2, s.getRazonSocial());
			stmt.setString(3, s.getDireccion());
			stmt.setInt(4, s.getId());
			stmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar actualizar la tabla Sponsors");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar actualizar la tabla Sponsors");
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
			FactoryConnection.getInstancia().releaseConn();
		}
	}
	
	public void insert(Sponsor s) throws Exception{
		PreparedStatement stmt = null;
		String query = "INSERT INTO Sponsors (Cuit, RazonSocial, Direccion) VALUES (?, ?, ?)";
		try{
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, s.getCuit());
			stmt.setString(2, s.getRazonSocial());
			stmt.setString(3, s.getDireccion());
			stmt.execute();
		}
		catch(SQLException sqlex){
			throw new SQLException("Error al intentar ingresar el sponsor");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar agregar el sponsor");
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
			FactoryConnection.getInstancia().releaseConn();
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
			if (stmt!=null){
				stmt.close();
			}
			FactoryConnection.getInstancia().releaseConn();
		}
	}
}
