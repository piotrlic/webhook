package com.tomtom.ejb.helloworld.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomtom.ejb.helloworld.English;
import com.tomtom.ejb.helloworld.Greeter;

@WebServlet(urlPatterns = "/web")
public class Servlet extends HttpServlet {

	@Inject
//	@English
	Greeter greeter;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
		writer.append(greeter.hello());
		writer.append(" Current time: ");
		writer.append(String.valueOf(System.currentTimeMillis()));
		writer.flush();
		
	}
	
	

}
