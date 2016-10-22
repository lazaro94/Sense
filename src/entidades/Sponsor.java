package entidades;

public class Sponsor {
	
	private String razonSocial;
	private String cuit;
	private String calle;
	private String numero;
	private String comentario;
	private int id;
	
	//Constructores
	
	public Sponsor (int id, String razonSocial, String cuit, String direccion, String numero, String comentario){
		this.id=id;
		this.razonSocial=razonSocial;
		this.calle=direccion;
		this.cuit=cuit;
		this.numero=numero;
		this.comentario=comentario;
	}
	
	public Sponsor(){
		
	}
	
	//GETTERS Y SETTERS
	public String getComentario(){
		return comentario;
	}
	public void setComentario(String comentario){
		this.comentario=comentario;
	}
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
	public String getCalle() {
		return calle;
	}
	public void setCalle(String direccion) {
		this.calle = direccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	//Uso este override para llenar el comboBox
	@Override
	public String toString(){
		return this.getRazonSocial();
	}
	
}
