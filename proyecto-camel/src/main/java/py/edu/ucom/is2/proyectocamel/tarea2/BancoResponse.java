package py.edu.ucom.is2.proyectocamel.tarea2;

public class BancoResponse {
	String mensaje;
	String idtransaccion;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public BancoResponse BancoResponseValido (BancoClase banco) {
		BancoResponse respuesta = new BancoResponse();
		this.mensaje = "Mensaje Encolado";
		this.idtransaccion =String.valueOf(banco.ID);  
		respuesta.setMensaje(mensaje);
		respuesta.setIdtransaccion(idtransaccion);
		return respuesta;
	}
	
	public BancoResponse BancoResponseRechazado (BancoClase banco) {
		BancoResponse respuesta = new BancoResponse();
		this.mensaje = "Mensaje Rechazado";
		this.idtransaccion =String.valueOf(banco.ID);  
		respuesta.setMensaje(mensaje);
		respuesta.setIdtransaccion(idtransaccion);
		return respuesta;
	}

	public String getIdtransaccion() {
		return idtransaccion;
	}

	public void setIdtransaccion(String idtransaccion) {
		this.idtransaccion = idtransaccion;
	}
}