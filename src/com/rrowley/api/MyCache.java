package com.rrowley.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.rrowley.api.model.Start;

public class MyCache extends ConcurrentHashMap<String, Start> implements Runnable {
	private static final long serialVersionUID = 8834400389937530455L;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	private int expirySeconds, purgeInterval;
	private boolean keepAlive;

	public MyCache(int size, int expirySeconds, int purgeIntervalSeconds) {
		super(size);
		if (size < 1 || expirySeconds < 1 || purgeIntervalSeconds < 1) {
			throw new IllegalArgumentException("arguments must be > 0");
		}
		this.expirySeconds = expirySeconds;
		this.purgeInterval = purgeIntervalSeconds * 1000;
		keepAlive = true;
	}

	@Override
	public void run() {
		logger.info("Starting MyCache...");
		while (keepAlive) {
			for (String key : keySet()) {
				long cutoff = System.currentTimeMillis() - expirySeconds * 1000;
				if (get(key).getTime() < cutoff) {
					remove(key);
				}
			}
			try {
				Thread.sleep(purgeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Stopping MyCache...");
	}

	public void kill() {
		keepAlive = false;
	}

}
