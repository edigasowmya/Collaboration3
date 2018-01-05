package com.pro2.dao;

import java.util.List;

import com.pro2.model.Job;

public interface JobDAO {
	
public boolean createJob(Job job);

List<Job>getAllJobs();



}