package entidades;

import java.util.ArrayList;

public class Sponsor {
	
	private String razonSocial;
	private String cuit;
	private String calle;
	private String numero;
	private String comentario;
	private int id;
	private ArrayList<Contacto> contactos;
	private ArrayList<Contrato> contratos;
	
	//Constructores
	
	public Sponsor (int id, String razonSocial, String cuit, String direccion, String numero, String comentario){
		this.id=id;
		this.razonSocial=razonSocial;
		this.calle=direccion;
		this.cuit=cuit;
		this.numero=numero;
		this.comentario=comentario;
		contactos = new ArrayList<Contacto>();
		contratos = new ArrayList<Contrato>();
	}
	
	public Sponsor(){
		contactos = new ArrayList<Contacto>();
		contratos = new ArrayList<Contrato>();
		
	}
	public Sponsor(int id){
		this.id=id;
		contactos = new ArrayList<Contacto>();
		contratos = new ArrayList<Contrato>();
	}
	
	//GETTERS Y SETTERS
	public ArrayList<Contacto> getContactos(){
		return contactos;
	}
	public void setContactos(ArrayList<Contacto> contactos){
		this.contactos = contactos;
	}
	public void setContratos(ArrayList<Contrato> contratos){
		this.contratos = contratos;
	}
	public ArrayList<Contrato> getContratos(){
		return contratos;
	}
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
	
	@Override
	public boolean equals(Object obj){
		return obj instanceof Sponsor && ((Sponsor)obj).getId() == this.getId(); 
	}
	
}
