package entidades;

public class Contacto {

	private String nombre;
	private String apellido;
	private String telefono1;
	private String telefono2;
	private String dni;
	private String mail;
	private String cargo;
	private String direccion;
	
	public Contacto(){
		
	}
	public Contacto(String nombre, String apellido, String telefono1, String telefono2, String dni, String mail, String cargo, String direccion){
		this.apellido = apellido;
		this.cargo = cargo;
		this.direccion = direccion;
		this.dni = dni;
		this.mail = mail;
		this.nombre = nombre;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}	
	
}