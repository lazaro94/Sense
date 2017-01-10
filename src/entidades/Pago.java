package entidades;

import java.util.Date;

public class Pago {
	
	private Sponsor sponsor;
	private Contrato contrato;
	private String comprobante;
	private Date fechaPago;
	private Date fechaVenc;
	private Float recargo;
	private Float descuento;
	private Float importeAbonado;
	
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
	
	public Float getImporteAbonado() {
		return importeAbonado;
	}

	public void setImporteAbonado(Float abonado) {
		this.importeAbonado = abonado;
	}
	
	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Float Total(){
		Float subtotal = this.importeAbonado;
		if(descuento!=0){
			subtotal = (subtotal - (subtotal*(descuento/100)));
		}
		if(recargo!=0){
			subtotal = (subtotal + (subtotal*(descuento/100)));
		}
		return subtotal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fechaVenc.toString();
	}

}
