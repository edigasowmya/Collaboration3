package com.pro2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro2.dao.JobDAO;
import com.pro2.model.Job;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDAO jobDAO;

	public boolean createJob(Job job) {
		
		return jobDAO.createJob(job);
	}

	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobDAO.getAllJobs();
	}

	
	
}
	

