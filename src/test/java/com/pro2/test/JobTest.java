/*package com.pro2.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pro2.model.Job;
import com.pro2.service.JobService;

@Ignore
public class JobTest {
	
	private static Job job;
	
	@Autowired
	private static JobService jobsService;
	
	@BeforeClass
	public static void initialize(){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.pro2");
		context.refresh();
		
		jobsService = (JobService) context.getBean("jobsService");
	}

	
	@Test
	public void createJobsTest(){
		
		Job job = new Job();
		job.setJobDescription("Responsible for coding, testing and deploying user friendly applications");
		job.setJobTittle("Software Engineer");
		job.setSkillRequried("B.Tech");
		job.setCompanyName("XYZ Pvt Ltd");
		job.setLocation("Hyderabad");
		job.setYearsofExperience("2+ Yrs");
		job.setSalary("250000");
		job.setPostedon(new Date());
		
		assertTrue("Problem in storing Job details",jobsService.addJobs(job));
	}
	
	@Ignore
	@Test
	public void updateJobsTest(){
		
		Job job = new Job();
		job.setId(142);
		job.setJobDescription("Responsible for coding, testing and deploying user friendly applications");
		job.setJobTittle("Software Engineer");
		job.setSkillRequried("B.Tech");
		job.setCompanyName("ABC Pvt Ltd");
		job.setLocation("Chennai");
		job.setYearsofExperience("4+ Yrs");
		job.setSalary("450000");
		job.setPostedon(new Date());
		
		assertTrue("Problem in updating job details",jobsService.updateJobs(job));
	}
	
	@Ignore
	@Test
	public void deleteJobsTest(){
		
		Job job = new Job();
		job.setId(142);
		job.setJobDescription("Responsible for coding, testing and deploying user friendly applications");
		job.setJobTittle("Software Engineer");
		job.setSkillRequried("B.Tech");
		job.setCompanyName("XYZ Pvt Ltd");
		job.setLocation("Hyderabad");
		job.setYearsofExperience("2+ Yrs");
		job.setSalary("250000");
		job.setPostedon(new Date());
		
		assertTrue("Problem in deleting job details",jobsService.deleteJobs(job));
	}
	@Ignore
	@Test
	public void getJobTest(){
		
		Job job = jobsService.getJobById(141);
		assertNotNull("Problem in retrieving Job details",job);
		System.out.println("Job Id : "+job.getId()+"  Job Desc: "+job.getJobDesc()+"  Job Profile : "+job.getJobTitle()+"  Job Qualification : "+job.getSkillsRequired()+"  Job "+job.getPostedOn());
		
	}
	
	@Ignore
	@Test
	public void getAllJobsTest(){
		
		List<JobDetail> jobsList = jobsService.getAllJobs();
		assertNotNull("Problem in retrieving All Jobs details",jobsList);
		showJobsDetails(jobsList);
		
	}

	private void showJobsDetails(List<JobDetail> jobsList) {
		
		for (JobDetail jobs : jobsList) {
			
			System.out.println(" Job Id : "+jobs.getId());
			System.out.println(" Job Desc : "+jobs.getJobDesc());
			System.out.println(" Job Profile : "+jobs.getJobTitle());
			System.out.println(" Job Qualification : "+jobs.getSkillsRequired());
			System.out.println(" Job Posted Date : "+jobs.getPostedOn());
			
		}
		
	}
*/