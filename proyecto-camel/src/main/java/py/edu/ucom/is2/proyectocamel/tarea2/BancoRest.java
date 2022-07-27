package py.edu.ucom.is2.proyectocamel.tarea2;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import org.apache.camel.model.dataformat.JsonDataFormat;
@Component
public class BancoRest extends RouteBuilder {
	@Autowired
	bancoService service;
	JacksonDataFormat JsonDataFormat;

	@Override
	public void configure() throws Exception {
		
		//restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		JsonDataFormat   = new JacksonDataFormat(py.edu.ucom.is2.proyectocamel.tarea2.BancoClase.class);
		rest().path("/api")
		.consumes("application/json")
		.produces("application/json")
		
			//.get("/saludar")
			//.to("direct:enviar")
			
		.post("/enviar-transferencia")
		
			.type(py.edu.ucom.is2.proyectocamel.tarea2.BancoClase.class)
			.outType(BancoResponse.class)
			
			.to("direct:procesar-transferencia");
		
		
		
		from("direct:procesar-transferencia")
		
			.bean(service,"validar")
			
			
			.filter().method(FiltroTransferencia.class,"validar")
				.to("direct:procesarSwitch")
				
				//beanretorno
				.stop()
				//monto excede
				.end()
			
				.to("log:nook");
					//.setExchangePattern(ExchangePattern.InOut)
					//.transform().simple("El monto excede los 20 millones. EL ID ES= " + "${body.getID}");
		from("direct:procesarSwitch")
		
		
			.choice()
				.when(header("BANCO_DESTINO").contains("Itau"))
					.setExchangePattern(ExchangePattern.InOnly)						
					.marshal(JsonDataFormat)
					.log("${body}")
					.to("activemq:Canclini-ITAU-IN")
					//.setExchangePattern(ExchangePattern.InOut)
					//.transform().simple("Transferencia Exitosa a Itau. Los datos de la transferencia son:"+ "${body}")
					.endChoice()
					
				.when(header("BANCO_DESTINO").contains("Atlas"))
					.setExchangePattern(ExchangePattern.InOnly)
					.marshal(JsonDataFormat)				
					.to("activemq:Canclini-ATLAS-IN")
					//setExchangePattern(ExchangePattern.InOut)
					//.transform().simple("Transferencia Exitosa a ATLAS. Los datos de la transferencia son:"+ "${body}")
					.endChoice()
					
				.when(header("BANCO_DESTINO").contains("BASA"))
					.setExchangePattern(ExchangePattern.InOnly)
					.marshal(JsonDataFormat)				
					.to("activemq:Canclini-BASA-IN")
					//.setExchangePattern(ExchangePattern.InOut)
					//.transform().simple("Transferencia Exitosa a BASA.  Los datos de la transferencia son:\"+ \"${body}\"")
					.endChoice()
				.otherwise()
				//.transform().constant("El Banco destino no es valido")
		 ;
		
		
		
		
	}

}

//http://localhost:8080/camel/api/enviar


