package com.thread.safe.demo;

import java.util.LinkedList;

public class ProcessorImpl implements Processor, Runnable {

	public ProcessorImpl() {
		this.processQueue();
	}

	private LinkedList<Runnable> procQueue = new LinkedList<Runnable>();

	@Override
	public void process(Runnable runJob) {

		this.addToQueue(runJob);

	}

	public void addToQueue(Runnable job) {
		synchronized (procQueue) {
			procQueue.add(job);

			// Wake up the thread which is currently awaiting the monitor
			procQueue.notify();
		}
	}

	public void processQueue() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {	    
			while(procQueue.isEmpty()) {
				synchronized (procQueue) {		    
					try {
						System.out.println("Queue is empty...");
						procQueue.wait();

						// Waiting for further input from user
						System.out.println("Waiting...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			Job curr = (Job) procQueue.remove();
			curr.run();

		}

	}

}
