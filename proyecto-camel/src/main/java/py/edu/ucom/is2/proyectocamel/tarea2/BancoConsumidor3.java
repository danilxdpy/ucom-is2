package py.edu.ucom.is2.proyectocamel.tarea2;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.rest.bean.AlumnoService;
import py.edu.ucom.is2.proyectocamel.tarea1.BancoClase;

@Component
public class BancoConsumidor3 extends RouteBuilder  {
	@Autowired
	
	bancoService banco;
	JacksonDataFormat JsonDataFormat;
	AgregarHeaderProcessor2 agregar;
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		JsonDataFormat   = new JacksonDataFormat(py.edu.ucom.is2.proyectocamel.tarea2.BancoClase.class);
		
		from("activemq:Canclini-BASA-IN")
		
		//.transform().simple("consumidor ID 1> ${body}")
		//.bean("Transaccion recibida desde" + BancoClase.BANCO_ORIGEN+ "a " + BancoClase.BANCO_DESTINO )
		//.unmarshal().json(JsonLibrary.Jackson, bancoClase.class)
		//.log("${body}")
		.unmarshal(JsonDataFormat)
		.log("${body}")
		//.log("${body}")
		//.bean("Transaccion recibida desde" + BancoClase.BANCO_ORIGEN+ "a " + BancoClase.BANCO_DESTINO )
		.process(new AgregarHeaderProcessor2())
		.filter().method(FiltroTransferencia.class,"validarFecha")
			.to("direct:procesarConsumo3")
			.stop()
		.end()
				.to("direct:rechazarSwitch3");
		
		
			from("direct:procesarConsumo3")
			
			.choice()
			.when(header("BANCO_ORIGEN").contains("ATLAS"))	
				.bean(BancoResponse.class,"BancoResponseValido")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)
				.log("${body}")
				.to("activemq:Canclini-ATLAS-OUT?explicitQosEnabled=true&timeToLive=1000")
				.setExchangePattern(ExchangePattern.InOut)
				.transform().simple("${body}")
			.endChoice()			
		
				.when(header("BANCO_ORIGEN").contains("ITAU"))	
				.bean(BancoResponse.class,"BancoResponseValido")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)
				.log("${body}")
				.to("activemq:Canclini-ITAU-OUT?explicitQosEnabled=true&timeToLive=1000")
				.setExchangePattern(ExchangePattern.InOut)
				.transform().simple("${body}")
				
			.endChoice()
			
		
			
			.otherwise()
				.bean(BancoResponse.class,"BancoResponseRechazado")
				.setExchangePattern(ExchangePattern.InOut)
				.marshal().json(JsonLibrary.Jackson)
				.log("rechazado dentro del filtro")
				.transform().simple("${body}");
				
				
			from("direct:rechazarSwitch3")
			.bean(BancoResponse.class,"BancoResponseRechazado")
			.setExchangePattern(ExchangePattern.InOut)
			.marshal().json(JsonLibrary.Jackson)
			.log("rezhazado fuera del filtro")
			.transform().simple("asdsadsa")
		
		//.bean(banco)
		//.transform().simple("${body} del Banco A")
		//.to("log:is2log");
		
		//from("activemq:Canclini-BancoB-IN")
		//.unmarshal(JsonDataFormat)
		//.bean(banco)
		//.transform().simple("${body} del Banco B")
		//.to("log:is2log");
		;
		//from("activemq:topic:Canclini-BancoA-IN");
		
		
			}
	   
}
