package py.edu.ucom.is2.proyectocamel.tarea1;
import org.springframework.stereotype.Component;


@Component
public class bancoService {

	
		public String insertarBanco(bancoClase banco ) {
				BancoResponse respuesta = new BancoResponse();
				//CONECTARSE A LA BASE DE DATOS
				respuesta.setMensaje("Banco de Origen: "+banco.getBANCO_ORIGEN() + " Banco Destino; "+ banco.getBANCO_DESTINO() + " Monto:" + banco.getMONTO() + " Cuenta:" + banco.getCUENTA());
				return respuesta.getMensaje() + "Recibido exitosamente";
		}
	}

