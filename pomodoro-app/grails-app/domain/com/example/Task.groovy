package com.example

import grails.rest.Resource

class Task {

	String summary
	String details
	Date deadLine
	Integer priority

	static hasMany = [tags: Tag]

	static constraints = {
		summary maxSize: 50, blank: false
		details maxSize: 500, blank: true, nullable: true
		priority min: 0, max: 10, nullable: true
	}
}
