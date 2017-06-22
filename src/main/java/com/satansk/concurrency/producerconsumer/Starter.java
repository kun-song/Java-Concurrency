package com.satansk.concurrency.producerconsumer;

public class Starter {
	public static void main(String[] args) {
		// 线程共享变量
		Drop drop = new Drop();

		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}