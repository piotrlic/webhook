package com.tomtom.ejb.helloworld;

import javax.inject.Named;

@Named
@English
public class EnglishGreeter implements Greeter { 

	public String hello() {
		return "Hello world!";
	}
	
}
