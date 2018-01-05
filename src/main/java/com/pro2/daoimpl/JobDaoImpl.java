package com.pro2.daoimpl;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pro2.dao.JobDAO;
import com.pro2.model.Job;

@Repository
public class JobDaoImpl implements JobDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	

	public boolean createJob(Job job) {
	 Session session=sessionFactory.openSession();
	 Transaction tx=session.beginTransaction();
	 session.saveOrUpdate(job);
	 tx.commit();
	 
	 return true;
	}



		public List<Job> getAllJobs() {
		 Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 List<Job>listjob=session.createQuery("from Job").list();
		 tx.commit();
		 return listjob;
	}
	
	
	
}
