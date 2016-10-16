package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.AppException;

public class FactoryConnection {

	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="192.168.1.2";
	private String port="3306";
	private String user="sense";
	private String pass="sense123";
	private String db="sense";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConnection() throws AppException{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException cnfex) {
			throw new AppException("Error del Driver JDBC",cnfex);
		}
	}
	
	private static FactoryConnection instancia;
	
	public static FactoryConnection getInstancia() throws AppException{
		if (instancia==null){
			instancia = new FactoryConnection();
		}
		return instancia;
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} catch (SQLException sqlex) {
			new AppException("Error al conectar a la DB", sqlex);

		}
		return conn;
	}
	
	public void releaseConn() throws AppException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException sqlex) {
			throw new AppException("Error al cerrar conexión",sqlex);
		}
		
}
}
