package com.genarc.model.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.genarc.model.BaseEntity;

@XmlRootElement
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@XmlElement
	@Column(name = "summary", nullable = false, length = 50)
	private String summary;
	
	@XmlElement
	@Column(name = "deadLine", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deadLine;
	
	@XmlElement
	@Column(name = "description", nullable = true, length = 500)
	private String description;
	
	@XmlElement
	@Column(name = "priority", nullable = true)
	private Integer priority;
	
	@XmlTransient
	@ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
	private Collection<Tag> tags;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

}
