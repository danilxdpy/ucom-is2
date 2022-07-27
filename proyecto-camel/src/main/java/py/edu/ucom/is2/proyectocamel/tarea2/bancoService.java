package py.edu.ucom.is2.proyectocamel.tarea2;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class bancoService {

	public int id;
	
		public BancoClase validar(BancoClase banco ) {
			Random r = new Random();
		    ;
			banco.ID = r.nextInt((1000 - 1) + 1) ;
			
				return banco;
				
		}
		
		public int id(BancoClase banco ) {
		
				return banco.ID;
				
		}


	}
