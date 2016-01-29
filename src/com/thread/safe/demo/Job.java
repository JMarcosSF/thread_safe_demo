package com.thread.safe.demo;

public class Job implements Runnable {

    private int jobID;
    
    public Job(int id) {
	setJobID(id);
    }
    
    @Override
    public void run() {
    	System.out.println("My ID is: " + this.getJobID());
    	System.out.println("And I am doing some important processing...\n");
    }

    public int getJobID() {
	return jobID;
    }

    public void setJobID(int jobID) {
	this.jobID = jobID;
    }

}
