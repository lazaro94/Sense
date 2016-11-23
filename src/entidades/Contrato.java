package entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import util.AppException;

public class Contrato {
	
	private Date fechaInicio;
	private Date fechaFin;
	private int diaPago;
	private Float monto;
	private String comentario;
	private String descripcion;
	private String codigo;
	private int id;
	private Sponsor sponsor;
	private ArrayList<Pago> pagos;
	
	public Contrato(){
		sponsor = new Sponsor();
		pagos = new ArrayList<Pago>();
	}
	
	public Contrato(Date fechaInicio, Date fechaFin, int diaPago, Float monto, String comentario, String codigo, int id, int idSponsor, String descripcion){
		this.codigo = codigo;
		this.comentario = comentario;
		this.diaPago = diaPago;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.id = id;
		this.monto = monto;
		this.descripcion = descripcion;
		sponsor = new Sponsor();
		sponsor.setId(idSponsor);
		pagos = new ArrayList<Pago>();
	}
	
	@Override
	public String toString(){
		return this.getCodigo();
	}
	
	@Override
	public boolean equals(Object obj){
		return obj instanceof Contrato && ((Contrato)obj).getId() == this.getId(); 
	}
	
	public void generarPagos() throws Exception{
		int startMes=0;
		int endMes=0;
		int diffMonth=0;
		try{
			// Creo 2 objetos calendario para restar los meses //
			Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(this.fechaInicio);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(this.fechaFin);
            
            // Calculo la diferencia en meses entre la fecha de inicio y la de fin //
            startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            diffMonth = endMes - startMes;
            
            if(diffMonth<=0){
            	throw new AppException("La diferencia entre fechas es negativa");
            }
            
            // Por cada mes creo una pago con fecha de vencimiento el mes correspondiente y el dia de pago ingresado //
            for(int i=0; i<=diffMonth; i++){
            	Pago p = new Pago();
            	p.setContrato(this);
            	p.setSponsor(this.sponsor);
            	Date fecha = new Date(startCalendar.get(Calendar.YEAR)-1900, startCalendar.get(Calendar.MONTH), this.diaPago);
            	p.setFechaVenc(fecha);
            	this.pagos.add(p);
            	startCalendar.add(Calendar.MONTH, 1);
            }
            
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public String getFechaInicio(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(this.fechaInicio);
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public String getFechaFin(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(this.fechaFin);
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public Sponsor getSponsor(){
		return this.sponsor;
	}
	public void setSponsor (Sponsor sponsor){
		this.sponsor=sponsor;
	}
	public ArrayList<Pago> getPagos(){
		return this.pagos;
	}
	public void setPagos(ArrayList<Pago> pagos){
		this.pagos=pagos;
	}
}
