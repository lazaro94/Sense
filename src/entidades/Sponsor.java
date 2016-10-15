package entidades;

public class Sponsor {
	
	private String razonSocial;
	private String cuit;
	private String direccion;
	private int id;
	
	//Constructores
	
	public Sponsor (int id, String razonSocial, String cuit, String direccion){
		this.id=id;
		this.razonSocial=razonSocial;
		this.direccion=direccion;
		this.cuit=cuit;
	}
	
	public Sponsor(){
		
	}
	
	//GETTERS Y SETTERS
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
