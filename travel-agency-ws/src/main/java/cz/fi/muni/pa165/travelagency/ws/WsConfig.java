package cz.fi.muni.pa165.travelagency.ws;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

/**
 *
 * @author Michal Holic
 */
@EnableWs
@Configuration
@ComponentScan("cz.fi.muni.pa165.travelagency.ws")
public class WsConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
		final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/*");
	}

	@Bean(name = "trips")
	public DefaultWsdl11Definition productsWsdl11Definition(XsdSchema tripsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("tripsPort");
		wsdl11Definition.setLocationUri("/");
		wsdl11Definition.setTargetNamespace("http://muni.fi.cz/pa165/ws/entities/trips");
		wsdl11Definition.setSchema(tripsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema tripsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Trip.xsd"));
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(this.payloadValidatingInterceptor());
	}

	@Bean
	public PayloadValidatingInterceptor payloadValidatingInterceptor() {
		final PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
		interceptor.setXsdSchema(this.tripsSchema());
		interceptor.setValidateRequest(true);
		interceptor.setValidateResponse(true);
		return interceptor;
	}
}