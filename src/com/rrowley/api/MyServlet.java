package com.rrowley.api;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rrowley.api.model.End;
import com.rrowley.api.model.Start;

@WebServlet("/api")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 8319325733170278030L;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	public MyServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long time = System.currentTimeMillis();
		Map<String, String[]> queryParams = request.getParameterMap();
		String user = String.join("", queryParams.getOrDefault("user", new String[] { "" }));
		String resourceId = String.join("", queryParams.getOrDefault("resource", new String[] { "" }));
		Start start = new Start(time, user, resourceId);
		logger.info(start.toString());
		((MyCache) getServletContext().getAttribute("cache")).put(user, start);
		response.setStatus(204);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long time = System.currentTimeMillis();
		Map<String, String[]> queryParams = request.getParameterMap();
		String user = String.join("", queryParams.getOrDefault("user", new String[] { "" }));
		String interest = String.join("", queryParams.getOrDefault("interest", new String[] { "" }));
		End end = new End(time, user, interest);
		logger.info(end.toString());
		((MyQueue) getServletContext().getAttribute("queue")).add(end);
		response.setStatus(204);
	}

}
