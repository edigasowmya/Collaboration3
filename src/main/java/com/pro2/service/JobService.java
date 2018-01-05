package com.pro2.service;

import java.util.List;

import com.pro2.model.Job;

public interface JobService {
	
	
	public boolean createJob(Job job);
	
	List<Job>getAllJobs();


}