package logica;

import java.sql.SQLException;

import com.data.sponsor.DataContacto;

import entidades.Contacto;

public class LogicContacto {

	private DataContacto dc = null;
	
	public void insertContacto(Contacto c) throws Exception{
		dc = new DataContacto();
		try{
			dc.insert(c);
		}
		catch (SQLException sqlex){
			throw sqlex;
		}
		catch (Exception ex){
			throw ex;
		}
	}
	
	public void updateContacto(Contacto c) throws Exception{
		dc = new DataContacto();
		try{
			dc.update(c);
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public void deleteContacto(Contacto c) throws Exception{
		dc = new DataContacto();
		try{
			dc.delete(c);
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
