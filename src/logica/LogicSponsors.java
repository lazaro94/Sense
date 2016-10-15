package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import datos.DataSponsor;
import entidades.Sponsor;

public class LogicSponsors {
	
	DataSponsor ds = null;
	
	public ArrayList<Sponsor> getSponsors() throws Exception{
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		ds= new DataSponsor();
		
		try{
			sponsors=ds.getSponsors();
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
		return sponsors;
	}
	
	public void updateSponsor(Sponsor s) throws Exception{
			ds = new DataSponsor();
			
			try{
				ds.update(s);
			}
			catch(SQLException sqlex){
				throw sqlex;
			}
			catch(Exception ex){
				throw ex;
			}			
		}
	
	public void InsertSponsor(Sponsor s) throws Exception{
		ds = new DataSponsor();
		
		try{
			ds.insert(s);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public void DeleteSponsor(Sponsor s) throws Exception{
		ds = new DataSponsor();
		
		try{
			ds.delete(s);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
