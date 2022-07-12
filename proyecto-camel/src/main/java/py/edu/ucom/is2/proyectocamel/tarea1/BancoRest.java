package py.edu.ucom.is2.proyectocamel.tarea1;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class BancoRest extends RouteBuilder {




	@Override
	public void configure() throws Exception {
		//restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
		restConfiguration().component("servlet").bindingMode(RestBindingMode.off);
		rest().path("/api")
		.consumes("application/json")
		.produces("application/json")
		
			//.get("/saludar")
			//.to("direct:enviar")
			
			.post("/enviar-transferencia")
			.to("direct:procesar-transferencia");
		
			//.post("encolar")
			//.to("direct:encolar");
		
		
		//from("direct:enviar").transform().constant("Hola mundo");
		//from("direct:procesar-saludo").transform().simple("Recibido ${body}");
		
		
		
		//Route que va a procesar el post enviar
		/*from("direct:procesar-transferencia")
		.setExchangePattern(ExchangePattern.InOnly) //Importante el Exchange que se le pasa al MQ debe ser tel tipo Evento o sea InOnly
	    .to("activemq:Canclini-BancoA-IN")
	    .setExchangePattern(ExchangePattern.InOut) 
	    .transform().simple("Mensaje encolado exitosamente");*/
		from("direct:procesar-transferencia")
		.setExchangePattern(ExchangePattern.InOnly) 
			.choice()
			.when(header("BANCO_DESTINO").contains("BancoA")).to("activemq:Canclini-BancoA-IN").endChoice()
			.when(header("BANCO_DESTINO").contains("BancoB")).to("activemq:Canclini-BancoB-IN").endChoice()
			.otherwise()
				.transform().constant("El valor enviado no ex valido")
		.setExchangePattern(ExchangePattern.InOut) ;
		
		
		
		
	}

}

//http://localhost:8080/camel/api/enviar



