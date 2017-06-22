package com.satansk.concurrency.producerconsumer;

import java.util.Random;

public class Consumer implements Runnable {
	// 线程共享数据
	private Drop drop;

	public Consumer(Drop drop) {
		this.drop = drop;
	}

	public void run() {
		Random random = new Random();

		for (String message = drop.take(); ! message.equals("DONE"); message = drop.take()) {
			System.out.format("Consumer received: %s%n", message);

			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
	}
}
