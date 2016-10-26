package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.DataContacto;
import entidades.Contacto;
import entidades.Sponsor;

public class LogicContacto {

	private DataContacto dc = null;
	
	public void insertContacto(Contacto c, Sponsor s) throws Exception{
		dc = new DataContacto();
		try{
			dc.insert(c, s);
		}
		catch (SQLException sqlex){
			throw sqlex;
		}
		catch (Exception ex){
			throw ex;
		}
	}
	
	public ArrayList<Contacto> getContactos(Sponsor s) throws Exception{
		dc = new DataContacto();
		ArrayList<Contacto> contactos = new ArrayList<Contacto>();
		
		try{
			contactos=dc.contactos(s);
		}
		catch (SQLException sqlex){
			throw sqlex;
		}
		catch (Exception ex){
			throw ex;
		}
		return contactos;
	}
}
