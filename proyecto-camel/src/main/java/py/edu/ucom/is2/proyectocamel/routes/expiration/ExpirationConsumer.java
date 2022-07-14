package py.edu.ucom.is2.proyectocamel.routes.expiration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ExpirationConsumer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:expirationTest")
		.log("Mensaje recibido ${body}")
		.end();//.to("log:is2log");
	}
}
