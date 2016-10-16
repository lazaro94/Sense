package logica;

import java.sql.Date;
import java.sql.SQLException;

import datos.DataSponsor;
import entidades.Sponsor;

public class LogicPublicidad {
	
	private DataSponsor ds = null;
	
	public void createPublicidad(Sponsor s, Date fechaInicio, Date fechaFin, int diaPago, Double monto) throws Exception{		
		ds = new DataSponsor();
		try{
			s=ds.getIdByRS(s);
			ds.insertPublicidad(s, fechaInicio, fechaFin, diaPago, monto);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}

}
