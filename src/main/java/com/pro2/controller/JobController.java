
package com.pro2.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pro2.model.Job;
import com.pro2.model.UsersDetails;
import com.pro2.service.JobService;
import com.pro2.model.ErrorClazz;

@RestController
public class JobController{
	
	@Autowired
	private JobService jobService;

	public JobController(){
		System.out.println("jobController is Instantiated");
	}
	
	@RequestMapping(value="/addjob", method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session)throws Exception
	{
		
		UsersDetails u=(UsersDetails)session.getAttribute ("validUser");
		System.out.println(u.getRole());
		if(u.getRole().equals("ADMIN"))
		{
		
			jobService.createJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		return new ResponseEntity<ErrorClazz>(new ErrorClazz(8,"Access Denied"),HttpStatus.UNAUTHORIZED);
	
	}
		
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
		public ResponseEntity<?> getAllJobs(HttpSession session)
		{
			UsersDetails u =(UsersDetails)session.getAttribute("validUser");
			if(u==null)
			{
				ErrorClazz error=new ErrorClazz(6,"UnAuthorized user");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
			}
			List<Job> jobs=jobService.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		}
		

/*	@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
	    UsersDetails u=(UsersDetails)session.getAttribute("validUser");
	    if(u==null){
	         ErrorClazz error=new ErrorClazz(9,"UnAuthorized user");
	            return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	    }
	    Job job=jobService.getJobById(1);
	    return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	*/
	}
