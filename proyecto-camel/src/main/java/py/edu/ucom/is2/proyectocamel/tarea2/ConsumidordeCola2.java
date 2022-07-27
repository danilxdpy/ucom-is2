package py.edu.ucom.is2.proyectocamel.tarea2;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ConsumidordeCola2 extends RouteBuilder {
	@Autowired
	
	bancoService banco;
	JacksonDataFormat JsonDataFormat;
	AgregarHeaderProcessor2 agregar;
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		
		from("activemq:Canclini-ATLAS-OUT")
		.log("${body}")
		
		.setExchangePattern(ExchangePattern.InOut)
		.transform().simple("${body}");
}
}
