package com.super20.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;

	public Project(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Project(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		return "Project [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
