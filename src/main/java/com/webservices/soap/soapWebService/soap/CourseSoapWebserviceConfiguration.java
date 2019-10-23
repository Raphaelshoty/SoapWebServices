package com.webservices.soap.soapWebService.soap;

import java.util.Collections;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;

import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//enable spring web services
@EnableWs // enable webservices on the project
//inform that this class is a spring configuration file
@Configuration
public class CourseSoapWebserviceConfiguration extends WsConfigurerAdapter {

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
	
	//xwsSecurityInterceptor
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
		//callback handler -> SimplePasswordValidationCallbackHandler
		interceptor.setCallbackHandler(callbackHandler());
		//security policy -> securityPolicy.xml
		interceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return interceptor;
	}
	
	
	@Bean	
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password")); // this can be changed to get user and password from a database		
		return handler;
	}

	//interceptors.add -> xwsSecurityInterceptor -> from WsConfigurerAdapter
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
	
}
