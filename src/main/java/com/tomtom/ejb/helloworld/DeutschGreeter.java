package com.tomtom.ejb.helloworld;

import javax.inject.Named;

@Named
public class DeutschGreeter implements Greeter {

	public String hello() {
		return "Guten tag!";
	}

}
