package com.pro2.daoimpl;

import java.util.Date;
import java.util.List;

//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pro2.dao.BlogDao;
import com.pro2.model.Blog;

@Repository
public class BlogDaoImpl implements BlogDao {
	
	@Autowired
	SessionFactory sessionFactory;


	public boolean addBlog(Blog blog) {
		try
		{
		    Session session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    session.save(blog);
		    tx.commit();
			return true;
		}catch(Exception e){
			System.out.println("Exception raised: "+e);
			return false;
		}
	}

	@Transactional
	public boolean updateBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e){
			System.out.println("Exception raised: "+e);
			return false;
		}

	}

	@Transactional
	public Blog getBlog(int blogId) {
		
		Session session = sessionFactory.openSession();
			
		Blog blogObj = session.get(Blog.class, blogId);
		
		session.close();
		
		return blogObj;
	}

	@Transactional
	public List<Blog> getAllBlogs(int userId) {
		
		Session session = sessionFactory.openSession();
		
		List<Blog> blogList= session.createQuery("from Blog where userId = :userId")
				.setParameter("userId", userId).list();
		
		session.close();
		
		
		return blogList;
	}

	@Transactional
	public boolean approveBlog(Blog blog) {
		try{
			
			Blog blogObj = sessionFactory.getCurrentSession().load(Blog.class, blog.getBlogId());
			blogObj.setStatus("APPROVED");
			blogObj.setPublishDate(new Date());
			return true;
			
		}catch(Exception e){
			System.out.println("Exception raised: "+e);
			return false;
		}

	}

	@Transactional
	public boolean deleteBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
			
		}catch(Exception e){
			
			System.out.println("Exception raised: "+e);
			return false;
		}
	}

	@Transactional
	public List<Blog> getAllApprovedBlog() {
		Session session = sessionFactory.openSession();
		
		List<Blog> blogList= session.createQuery("from Blog where status = 'APPROVED'").list();
		
		session.close();
		
		return blogList;
	}

	@Transactional
	public boolean rejectBlog(Blog blog) {
		try{
			
			Blog blogObj = sessionFactory.getCurrentSession().load(Blog.class, blog.getBlogId());
			blogObj.setStatus("REJECTED");
			blogObj.setPublishDate(new Date());
			return true;
			
		  }catch(Exception e){
			System.out.println("Exception raised: "+e);
			return false;
		  }
	}

	@Transactional
	public List<Blog> getAllPendingBlogs() {

		Session session = sessionFactory.openSession();
		
		List<Blog> blogList= session.createQuery("from Blog where status = 'PENDING'").list();
		
		session.close();
		
		return blogList;
	}

	@Transactional
	public int updateNoOfLikes(Blog blog) {
		try{
			
			Blog blogObj = sessionFactory.getCurrentSession().load(Blog.class, blog.getBlogId());
			blogObj.setNoOfLikes(blog.getNoOfLikes());
			return blogObj.getNoOfLikes();
			
		  }catch(Exception e){
			System.out.println("Exception raised: "+e);
			return 0;
		  }
	}

	@Transactional
	public List<Blog> getAllBlogs() {
		
		Session session = sessionFactory.openSession();
		
		List<Blog> blogList= session.createQuery("from Blog where status != 'PENDING'").list();
		
		session.close();
		
		return blogList;
	}


}