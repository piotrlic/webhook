package com.tomtom.ejb.helloworld;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EnglishGreeterTest {

	@Inject
	Greeter greeter;
	
	@Test
	public void should_say_hello_in_english() {
		assertThat("Hello world!", is(equalTo(greeter.hello())));
	}
	
	@Deployment
	public static WebArchive create() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(Greeter.class, EnglishGreeter.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
}
