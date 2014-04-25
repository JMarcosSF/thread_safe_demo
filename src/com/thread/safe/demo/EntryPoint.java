package com.thread.safe.demo;
import java.util.ArrayList;

/**
 * @author Juan Marcos
 * 
 * A thread safe program which processes single threaded jobs within a queue.
 * 
 * Although there are multiple threads ("jobs") within the linked list 
 * which are instantiated and started, each thread will only be run and 
 * started if the preceding job has already been processed.
 * 
 * In this simple example, each job is sequentially given an ID from one 
 * through twenty, then is added into a queue for futher processing. 
 * The expected output in this case is that each job will print out it's 
 * assigned ID one line before notifying the awaiting thread that it's job is 
 * finished.
 * 
 * If there are no jobs in the queue, the program will await further entries.
 *
 */
public class EntryPoint {

	public static void main(String[] args) {
		ArrayList<Job> jobList = new ArrayList<Job>();

		for(int i = 1; i <= 20; i++) {
			Job tj = new Job(i);
			jobList.add(tj);
		}

		ProcessorImpl proc =  new ProcessorImpl();

		for(Job job:jobList) {	    
			proc.process(job);
		}

	}

}
