package py.edu.ucom.is2.proyectocamel.tarea2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FiltroTransferencia {

		public static boolean validar(BancoClase banco) {
			if (banco.MONTO < 20000000) {
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public static boolean validarFecha(BancoClase banco) {
			//banco.FECHA = "2014/09/12 00:00";
			//String stopInput = "2014/09/13 00:00";
			String output = LocalDate.now().toString();
			DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd" );

			LocalDate start = LocalDate.parse( banco.FECHA , f ) ;
			LocalDate stop = LocalDate.parse( output , f ) ;
			boolean esHoy = start.isEqual( stop ) ;
			
			return esHoy;
		}
}
