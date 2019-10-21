package com.webservices.soap.soapWebService.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.webservices.soap.soapWebService.soap.bean.Course;

@Component
public class CourseDetailsService {

	// mock that could be substituted by a service that consumes a database 
	
	private static List<Course> courses = new ArrayList<>(); 
	
	static {
		Course course1 = new Course(1, "spring boot", "spring boot from zero to hero");
		courses.add(course1); 
		Course course2 = new Course(2, "Spring restfull web services", "Spring webservices in spring boot");
		courses.add(course2); 
		Course course3 = new Course(3, "Soap Web services", "SOAP Web services");
		courses.add(course3); 
		Course course4 = new Course(4, "Spring MVC", "spring in three layers");
		courses.add(course4); 
		Course course5 = new Course(5, "spring data JPA", "A smart way to access data");
		courses.add(course5); 
	}
	
	// course 
	public Course findbyid(int id) {
		Course coursefound = null;
		for (Course course : courses) {
			if(course.getId() == id) {
				coursefound = course;
			}
		}
		return coursefound;
	}
	
	// findAll()
	
	public List<Course> findAll(){
		return courses;
	}
	
	//deleteById(int id)
	
	public int deleteById(int id) {		
		Iterator<Course> cIterator = courses.iterator();
		while(cIterator.hasNext()) {
			Course course = cIterator.next();
			if(course.getId() == id) {
				cIterator.remove();
				return 1;
			}
		}
		return 0;
	}
	
	//update course & new course
//	public boolean insertCourse(Course course) {
//		this.courses.contains(course) ? return false : return courses.add(course);
//	}
	
}
