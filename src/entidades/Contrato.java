package entidades;

import java.sql.Date;

public class Contrato {
	
	private Date fechaInicio;
	private Date fechaFin;
	private int diaPago;
	private Float monto;
	private String comentario;
	private String codigo;
	private int id;
	private int idSponsor;
	
	public Contrato(){
		
	}
	
	public Contrato(Date fechaInicio, Date fechaFin, int diaPago, Float monto, String comentario, String codigo, int id, int idSponsor){
		this.codigo = codigo;
		this.comentario = comentario;
		this.diaPago = diaPago;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.id = id;
		this.idSponsor = idSponsor;
		this.monto = monto;
	}
	
	@Override
	public String toString(){
		return this.getCodigo();
	}
	
	@Override
	public boolean equals(Object obj){
		return obj instanceof Contrato && ((Contrato)obj).getCodigo() == this.getCodigo(); 
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getDiaPago() {
		return diaPago;
	}
	public void setDiaPago(int diaPago) {
		this.diaPago = diaPago;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdSponsor() {
		return idSponsor;
	}
	public void setIdSponsor(int idSponsor) {
		this.idSponsor = idSponsor;
	}

}
