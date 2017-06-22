package com.satansk.concurrency.producerconsumer;

import java.util.Random;

public class Producer implements Runnable {
	// 线程共享数据
	private Drop drop;

	public Producer(Drop drop) {
		this.drop = drop;
	}

	public void run() {
		String importantInfo[] = {
				"Mares eat oats", 
				"Does eat oats", 
				"Little lambs eat ivy",
				"A kid will eat ivy too" };

		Random random = new Random();

		for (String message : importantInfo) {
			// 发送消息
			drop.put(message);

			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
		// 消息结束标志
		drop.put("DONE");
	}
}
