package com.webservices.soap.soapWebService.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapwebservices.courses.CourseDetails;
import com.soapwebservices.courses.DeleteCourseDetailsRequest;
import com.soapwebservices.courses.DeleteCourseDetailsResponse;
import com.soapwebservices.courses.GetAllCourseDetailsRequest;
import com.soapwebservices.courses.GetAllCourseDetailsResponse;
import com.soapwebservices.courses.GetCourseDetailsRequest;
import com.soapwebservices.courses.GetCourseDetailsResponse;
import com.soapwebservices.courses.InsertCourseDetailsRequest;
import com.soapwebservices.courses.InsertCourseDetailsResponse;
import com.webservices.soap.soapWebService.soap.bean.Course;
import com.webservices.soap.soapWebService.soap.service.CourseDetailsService;

@Endpoint // annotation to expose this class as an endpoint and accept calls to be processed
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	//http://soapwebservices.com/courses -- calls to this namespace will call the  getCourseDetailsRequest 
	// this call to the name above will fire the method below
	
	// create a method to process the request
	// input - the object created to input - GetCourseDetailsRequest
	// output - the object created by jaxb to be the response - GetCourseDetailsResponse
	
	// the annotation here tells the spring to accept this method as the method to respond the request
	// the annotation accepts the namespace and the name of the master tag on the request xml
	@PayloadRoot(namespace = "http://soapwebservices.com/courses",localPart = "getCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseRequest(@RequestPayload GetCourseDetailsRequest request) { // the requestpayload is what i´m sending to the method
		Course course = service.findbyid(request.getId());
		
		return mapCourse(course);
	}

	// this method was generated by selecting the logic responsible for get the course and assign it to the response and return it
	// refactor -> extract method
	private GetCourseDetailsResponse mapCourse(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		response.setCourseDetails(courseDetails);			
		return response;
	}
	
	// method to getall courses request 
	@PayloadRoot(namespace = "http://soapwebservices.com/courses",localPart = "getAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseRequest(@RequestPayload GetAllCourseDetailsRequest request) { // the requestpayload is what i´m sending to the method
		List<Course> coursesfound = service.findAll();
		
		return mapAllCourseDetails(coursesfound);		
		
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> coursesfound) {
		GetAllCourseDetailsResponse resp = new GetAllCourseDetailsResponse();
		List<CourseDetails> courses = new ArrayList<>();		
		coursesfound.stream().forEach(cf ->{
			CourseDetails cd = new CourseDetails();
			cd.setDescription(cf.getDescription());
			cd.setId(cf.getId());
			cd.setName(cf.getName());
			courses.add(cd);
		});
		
		resp.getCourseDetails().addAll(courses);
		return resp;
	}
	
	// method to delete course 
	@PayloadRoot(namespace = "http://soapwebservices.com/courses",localPart = "deleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processDeleteCourseRequest(@RequestPayload DeleteCourseDetailsRequest request) { // the requestpayload is what i´m sending to the method
		int status = service.deleteById(request.getId());
		
		DeleteCourseDetailsResponse resp = new DeleteCourseDetailsResponse();
		resp.setStatus(status);
		return resp;
	}
	
//	@PayloadRoot(namespace = "http://soapwebservices.com/courses",localPart = "insertCourseDetailsRequest")
//	@ResponsePayload
//	public InsertCourseDetailsResponse processInsertCourseRequest(@RequestPayload InsertCourseDetailsRequest request) { // the requestpayload is what i´m sending to the method
//		
//		
//			
//		
//	}
//	

}