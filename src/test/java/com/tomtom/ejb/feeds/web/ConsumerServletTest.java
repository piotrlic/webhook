package com.tomtom.ejb.feeds.web;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.kevinsawicki.http.HttpRequest;
import com.tomtom.ejb.feeds.model.FeedBuilder;

@RunWith(Arquillian.class)
public class ConsumerServletTest {

	@Test
	@RunAsClient
	public void should_consume_post_rss(@ArquillianResource URL url) {
		
		String body = HttpRequest.post(url.toExternalForm() + "/web/consume")
			.send("rss="+RSS_EXAMPLE)
			.body();
		
		assertThat("There are 1 articles in the feed", 
				is(equalTo(body)));
	}
	
	@Deployment
	public static WebArchive create() {
		File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
			.resolve("rome:rome:0.9")
			.withTransitivity()
			.asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsLibraries(libs)
				.addClass(ConsumerServlet.class)
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
