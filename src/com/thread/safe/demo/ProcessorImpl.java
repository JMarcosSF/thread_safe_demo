package com.thread.safe.demo;

import java.util.LinkedList;

public class ProcessorImpl implements Processor, Runnable {

	public ProcessorImpl() {
		this.processQueue();
	}

	private volatile LinkedList<Runnable> jobProcessList = new LinkedList<Runnable>();

	@Override
	public void process(Runnable runJob) {
		synchronized (jobProcessList) {
			jobProcessList.add(runJob);

			// Wake up the thread which is currently awaiting the monitor
			jobProcessList.notify();
		}
	}

	public void processQueue() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {	    
			while(jobProcessList.isEmpty()) {
				synchronized (jobProcessList) {
					try {
						System.out.println("Queue is empty...");
						jobProcessList.wait();

						// Waiting for further input from user
						System.out.println("Waiting...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// Gets the head of the LinkedList, and runs runs it
			Job curr = (Job) jobProcessList.remove();
			curr.run();

		}

	}

}
