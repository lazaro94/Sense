package entidades;

import java.util.Date;

public class Pago {
	
	private Sponsor sponsor;
	private Contrato contrato;
	private String comprobante;
	private Date fechaPago;
	private Date fechaVenc;
	private Float recargo;
	
	public Pago(){
		sponsor = new Sponsor();
		contrato = new Contrato();
	}
	
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaVenc() {
		return fechaVenc;
	}
	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}
	public Float getRecargo() {
		return recargo;
	}

	public void setRecargo(Float recargo) {
		this.recargo = recargo;
	}

}
