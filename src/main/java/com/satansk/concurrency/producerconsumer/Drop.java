package com.satansk.concurrency.producerconsumer;

public class Drop {
	// producer 发送给 consumer 的消息
	private String message;

	// 1. true 表示 consumer 需要等待 producer 发送数据
	// 2. false 表示 producer 需要等待 consumer 消费数据
	private boolean empty = true;

	public synchronized String take() {
		// 等待消息到来
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		// 修改状态
		empty = true;

		// 通知 producer 状态已改变，可以发送消息了
		notifyAll();

		return message;
	}

	public synchronized void put(String message) {
		// 等待消息为空
		while (!empty) {
			while (!empty) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
		}

		// 修改状态
		empty = false;

		// 保存消息
		this.message = message;

		// 通知 consumer 状态已改变，可以消费数据了
		notifyAll();
	}
}
