package logica;

import com.data.sponsor.DataPago;

import entidades.Contrato;

public class LogicRegistrarPago {
	
	private DataPago dp;

	public Contrato setPagos(Contrato c) throws Exception{
		dp = new DataPago();
		try{
			c=dp.getCuotas(c);
		}
		catch(Exception ex){
			throw ex;
		}
		return c;
	}
}
