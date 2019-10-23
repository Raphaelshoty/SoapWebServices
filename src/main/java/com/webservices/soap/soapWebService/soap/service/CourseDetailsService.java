package com.webservices.soap.soapWebService.soap.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.webservices.soap.soapWebService.soap.bean.Course;

@Component
@Transactional
public class CourseDetailsService { // this class/component could use the interface JpaRepositoty as well

	@Autowired
	EntityManager em;
	
	
	public enum Status{
		SUCCESS, FAILURE;
	}
	
//	private static List<Course> courses = new ArrayList<>(); 
//	
//	static {
//		Course course1 = new Course(1, "spring boot", "spring boot from zero to hero");
//		courses.add(course1); 
//		Course course2 = new Course(2, "Spring restfull web services", "Spring webservices in spring boot");
//		courses.add(course2); 
//		Course course3 = new Course(3, "Soap Web services", "SOAP Web services");
//		courses.add(course3); 
//		Course course4 = new Course(4, "Spring MVC", "spring in three layers");
//		courses.add(course4); 
//		Course course5 = new Course(5, "spring data JPA", "A smart way to access data");
//		courses.add(course5); 
//	}
	
	// course findbyId()
	public Course findbyid(int id) {
		return em.find(Course.class, id);
	}
	
	// findAll()
	
	public List<Course> findAll(){
		TypedQuery<Course> query = em.createQuery("Select c From Courses c", Course.class);
		return query.getResultList().stream().distinct().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
	}
	
	//deleteById(int id)
	
	public Status deleteById(int id) {	
		Course c = em.find(Course.class, id);
		if(Objects.nonNull(c)) {
			em.remove(c);
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}
	
	//update course & new course
	public boolean insertCourse(Course course) {			
		if(Objects.isNull( course.getId() ) ) {
			em.persist(course);
			return true;
		}else {
			em.merge(course);
			return true;
		}
	}
	
}
