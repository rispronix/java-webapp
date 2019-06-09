package com.rrowley.api;

import java.util.concurrent.ArrayBlockingQueue;

import com.rrowley.api.model.End;

public class MyQueue extends ArrayBlockingQueue<End> {
	private static final long serialVersionUID = 4450708612256340598L;

	public MyQueue(int capacity) {
		super(capacity);
	}

}
