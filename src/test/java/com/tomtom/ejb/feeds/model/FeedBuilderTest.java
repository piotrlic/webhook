package com.tomtom.ejb.feeds.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FeedBuilderTest {

	@Inject
	FeedBuilder builder;
	
	@Test
	public void should_build_feed_with_two_items() throws URISyntaxException,
			FileNotFoundException {
		Feed fromXml = builder.fromXml(RSS_EXAMPLE);

		assertThat("W3Schools Home Page", is(equalTo(fromXml.getTitle())));
	}
	
	@Deployment
	public static WebArchive create() {
		File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
			.resolve("rome:rome:0.9")
			.withTransitivity()
			.asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsLibraries(libs)
				.addClasses(FeedBuilder.class)
				.addPackage("com.tomtom.ejb.feeds.model")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	public final static String RSS_EXAMPLE = "<rss version=\"2.0\">" +
			"<channel>" +
			"<title>W3Schools Home Page</title>" +
			"<link>http://www.w3schools.com</link>" +
			"<description>Free web building tutorials</description>" +
			"<item>" +
				"<title>RSS Tutorial</title>" +
				"<link>http://www.w3schools.com/rss</link>" +
				"<description>New RSS tutorial on W3Schools</description>" +
			"</item>" +
		"</channel>" +
	"</rss>";

}
