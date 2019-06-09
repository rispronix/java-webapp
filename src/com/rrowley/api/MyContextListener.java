package com.rrowley.api;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(getClass().getName());

	private MyQueue queue;
	private MyCache cache;
	private Thread cacheThread, collatorThread;
	private MyEventCollator collator;

	public MyContextListener() {
		logger.info("Constructing MyContextListener...");
		cache = new MyCache(10000, 600, 60);
		cacheThread = new Thread(cache);
		queue = new MyQueue(10000);
		collator = new MyEventCollator(cache, queue);
		collatorThread = new Thread(collator);
		logger.info("Constructed MyContextListener...");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		cache.kill();
		cacheThread.interrupt();
		collator.kill();
		collatorThread.interrupt();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Initialising contextInitialized...");
		ServletContext context = arg0.getServletContext();
		context.setAttribute("cache", cache);
		context.setAttribute("queue", queue);
		cacheThread.start();
		collatorThread.start();
		logger.info("Initialised contextInitialized...");
	}

}
