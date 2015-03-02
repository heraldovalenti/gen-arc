package com.genarc.model.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.genarc.model.BaseEntity;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@XmlElement
	@Column(name = "name", length = 25, unique = true, nullable = false)
	private String name;
	
	@XmlTransient
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Task> tasks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

}
