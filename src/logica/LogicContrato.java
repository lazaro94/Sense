package logica;

import java.sql.Date;
import java.sql.SQLException;

import datos.DataContrato;
import datos.DataSponsor;
import entidades.Sponsor;

public class LogicContrato {
	
	private DataSponsor ds = null;
	private DataContrato dc = null;
	
	public void createPublicidad(Sponsor s, Date fechaInicio, Date fechaFin, int diaPago, Double monto, String codigo) throws Exception{		
		ds = new DataSponsor();
		dc = new DataContrato();
		try{
			s=ds.getIdByRS(s);
			
			dc.insertPublicidad(s, fechaInicio, fechaFin, diaPago, monto, codigo);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public void registrarPago(){
		
	}

}
