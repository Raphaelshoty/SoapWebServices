package com.webservices.soap.soapWebService.soap.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name="description")
	@NotBlank
	private String description;
	
	public Course() {}
	
	public Course(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s, description=%s]", id, name, description);
	}
	
	
	
	
}
