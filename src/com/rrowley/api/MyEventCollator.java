package com.rrowley.api;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import com.rrowley.api.model.CollatedEvent;
import com.rrowley.api.model.End;
import com.rrowley.api.model.Start;

public class MyEventCollator implements Runnable {
	private Logger logger = Logger.getLogger(getClass().getName());

	private MyCache cache;
	private ArrayBlockingQueue<End> queue;
	private boolean keepAlive;

	public MyEventCollator(MyCache cache, ArrayBlockingQueue<End> queue) {
		this.cache = cache;
		this.queue = queue;
		keepAlive = true;
	}

	@Override
	public void run() {
		logger.info("Starting MyEventCollator...");
		while (keepAlive) {
			try {
				End end = queue.take();
				Start start = cache.get(end.getUser());
				logger.info("Collating Start and End events, " + start + " " + end);
				CollatedEvent collatedEvent = new CollatedEvent(start, end);
				logger.info("Processed:" + collatedEvent);
//				WriteToDB();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Stopping MyEventCollator...");
	}

	public void kill() {
		keepAlive = false;
	}

}
