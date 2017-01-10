package entidades;

import java.util.ArrayList;
import java.util.Date;

public class ComprobantePagoSponsor {

	private int nroComprobante;
	private Date fechaEmision;
	private Contrato contrato;
	ArrayList<Pago> cuotas = null;
	
	public ComprobantePagoSponsor(){
		contrato = new Contrato();
		cuotas = new ArrayList<Pago>();
	}

	public int getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(int nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public ArrayList<Pago> getCuotas() {
		return cuotas;
	}

	public void setCuotas(ArrayList<Pago> cuotas) {
		this.cuotas = cuotas;
	}
}
