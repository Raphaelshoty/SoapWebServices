package com.webservices.soap.soapWebService.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//enable spring web services
@EnableWs // enable webservices on the project
//inform that this class is a spring configuration file
@Configuration
public class CourseSoapWebserviceConfiguration {

	// MessageDisptcherServlet
	// ApplicationContext
	// inform the URL to respond in this case will be -> /ws/*
	
	
	//º1
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	// /ws/courses.wsdl
	// courseDetails.xsd	
	
	// º3
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		// PortType - CoursePort
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://soapwebservices.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(courseSchema);
				
		return definition;
	}
	// namespace - http://soapwebservices.com/courses
	
	//º2
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("courseDetails.xsd"));
	}
	
}
