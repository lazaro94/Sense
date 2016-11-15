package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parse {

	/*public static Date dateToSql(java.util.Date fecha) throws Exception{
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = format.parse(fecha);
			sqlDate = new java.sql.Date(date.getTime());
		}
		catch(ParseException pex){
			throw new AppException("Error al intentar convertir las fechas");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar convertir las fechas");
		}
		return sqlDate;	
	}*/
}
