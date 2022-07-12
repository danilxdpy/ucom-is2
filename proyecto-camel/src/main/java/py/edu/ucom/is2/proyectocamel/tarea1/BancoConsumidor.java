package py.edu.ucom.is2.proyectocamel.tarea1;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.rest.bean.AlumnoService;
import py.edu.ucom.is2.proyectocamel.tarea1.bancoClase;

@Component
public class BancoConsumidor extends RouteBuilder  {
	@Autowired
	
	bancoService banco;
	JacksonDataFormat JsonDataFormat;
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		JsonDataFormat   = new JacksonDataFormat(bancoClase.class);
		
		from("activemq:Canclini-BancoA-IN")
		
		//.transform().simple("consumidor ID 1> ${body}")
		//.bean("Transaccion recibida desde" + BancoClase.BANCO_ORIGEN+ "a " + BancoClase.BANCO_DESTINO )
		//.unmarshal().json(JsonLibrary.Jackson, bancoClase.class)
		//.log("${body}")
		.unmarshal(JsonDataFormat)
		//.log("${body}")
		//.bean("Transaccion recibida desde" + BancoClase.BANCO_ORIGEN+ "a " + BancoClase.BANCO_DESTINO )
		.bean(banco)
		.transform().simple("${body} del Banco A")
		.to("log:is2log");
		
		from("activemq:Canclini-BancoB-IN")
		.unmarshal(JsonDataFormat)
		.bean(banco)
		.transform().simple("${body} del Banco B")
		.to("log:is2log");
		;
		//from("activemq:topic:Canclini-BancoA-IN");
}
}