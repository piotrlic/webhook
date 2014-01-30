package com.tomtom.ejb.feeds.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomtom.ejb.feeds.model.Feed;
import com.tomtom.ejb.feeds.model.FeedBuilder;

@WebServlet(urlPatterns = "/web/consume/*")
public class ConsumerServlet extends HttpServlet {

	private static final long serialVersionUID = -3793876752332268424L;

	@Inject
	FeedBuilder builder;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String rssbody = req.getParameter("rss");
		Feed feed = builder.fromXml(rssbody);

		req.setAttribute("feed", feed);
		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

}
