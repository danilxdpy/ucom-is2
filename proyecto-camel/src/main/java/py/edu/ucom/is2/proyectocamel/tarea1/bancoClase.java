package py.edu.ucom.is2.proyectocamel.tarea1;

public class bancoClase {
public String BANCO_ORIGEN;
public String CUENTA;
public String MONTO;
public String BANCO_DESTINO;

public String getBANCO_ORIGEN() {
	return BANCO_ORIGEN;
}


public void setBANCO_ORIGEN(String bANCO_ORIGEN) {
	BANCO_ORIGEN = bANCO_ORIGEN;
}


public String getCUENTA() {
	return CUENTA;
}


public void setCUENTA(String cUENTA) {
	CUENTA = cUENTA;
}


public String getMONTO() {
	return MONTO;
}


public void setMONTO(String mONTO) {
	MONTO = mONTO;
}


public String getBANCO_DESTINO() {
	return BANCO_DESTINO;
}


public void setBANCO_DESTINO(String bANCO_DESTINO) {
	BANCO_DESTINO = bANCO_DESTINO;
}


public bancoClase() {
	
}


public String print() {
	return "Recibido" + this.BANCO_DESTINO.toString();
	
}




}
