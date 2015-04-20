package com.example

import grails.rest.Resource

class Tag {

	String name

	static hasMany = [tasks: Task]

	static belongsTo = Task

	static constraints = {
		name maxSize: 50, blank: false, unique: true
	}
}
