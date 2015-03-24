package com.genarc.services.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.genarc.services.DataService;

public class GenarcApplication extends Application {

	public GenarcApplication() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resourceClasses = new HashSet<Class<?>>();
		resourceClasses.add(DataService.class);
		return resourceClasses;
	}
}