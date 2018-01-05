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

import com.pro2.model.Blog;
import com.pro2.model.BlogComment;
import com.pro2.model.ErrorClazz;
//import com.pro2.model.BlogComment;
import com.pro2.model.UsersDetails;
import com.pro2.service.BlogCommentService;
//import com.pro2.service.BlogCommentService;
import com.pro2.service.BlogService;
import com.pro2.service.UsersService;
//import com.pro2.utility.CollabApplicationError;

@RestController
public class BlogController {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	BlogCommentService blogCommentService;
	
	public BlogController(){
		System.out.println("jobController is Instantiated");
	}

	@RequestMapping(value="/createBlog",method=RequestMethod.POST)
	public ResponseEntity<?> createBlog(@RequestBody Blog blog,HttpSession session){
		
		UsersDetails u = (UsersDetails) session.getAttribute("validUser");
		 if(u==null)
		    {
		    	return new ResponseEntity<ErrorClazz>(new ErrorClazz(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
			}	   
		    else	
		    {
			//	UsersDetails user = userService.viewUser(userId);
				
				if(u.isIsonline()){
					blog.setUserId(u.getUser_id());
					blog.setUserName(u.getUserName());
					blog.setStatus("PENDING");
					blog.setCreatedDate(new Date());
					blog.setNoOfLikes(0);
					if(blogService.addBlog(blog)){
						return new ResponseEntity<Blog>(blog, HttpStatus.OK);
					}else{
						return new ResponseEntity<ErrorClazz>(new ErrorClazz(11,"Blog Creation failed"), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(12,"User must be logged in to create blog"),HttpStatus.CONFLICT);
			}
	}
	
	@RequestMapping(value="/viewBlogs",method=RequestMethod.GET)
	public ResponseEntity<?> viewBlogs(){
		List<Blog> blogList = blogService.getAllApprovedBlog();
		if(blogList!=null){
				return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(13,"Retrieving Blog details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/viewBlogById/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> viewBlogById(@PathVariable("blogId") int blogId){
		
		Blog blog = blogService.getBlog(blogId);
		
		if(blog!=null){
				System.out.println("Blog details: "+blog.getBlogId()+" Date: "+blog.getCreatedDate());
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(13,"Retrieving Blog details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/viewPendingBlogs",method=RequestMethod.GET)
	public ResponseEntity<?> viewPendingBlogs(){
		List<Blog> blogList = blogService.getAllPendingBlogs();
		if(blogList!=null){
				return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(13,"Retrieving Blog details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/viewAllBlogs",method=RequestMethod.GET)
	public ResponseEntity<?> viewAllBlogs(){
		List<Blog> blogList = blogService.getAllBlogs();
		if(blogList!=null){
				return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(13,"Retrieving Blog details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/viewUserBlogs",method=RequestMethod.GET)
	public ResponseEntity<?> viewUserBlogs(HttpSession session){
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId==null)
	    {
	    	return new ResponseEntity<ErrorClazz>(new ErrorClazz(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
				List<Blog> blogList = blogService.getAllBlogs(userId);
				if(blogList!=null){
						return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
				}
				else
				{
						return new ResponseEntity<ErrorClazz>(new ErrorClazz(13,"Retrieving Blog details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
				}
	    }
	}
	
	@RequestMapping(value="/approveBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> approveBlog(@PathVariable("blogId") int blogId){
		Blog blog = blogService.getBlog(blogId);
		if(blog!=null){				
			if(blogService.approveBlog(blog)){
				    blog = blogService.getBlog(blogId);
					return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
					return new ResponseEntity<ErrorClazz>(new ErrorClazz(14,"Error occured during approving Blog details"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(15,"No Blogs found for given Id"), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/rejectBlog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> rejectBlog(@PathVariable("blogId") int blogId){
		Blog blog = blogService.getBlog(blogId);
		
		if(blog!=null){
			
			if(blogService.rejectBlog(blog)){
					blog = blogService.getBlog(blogId);
					return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
					return new ResponseEntity<ErrorClazz>(new ErrorClazz(14,"Error occured during rejecting Blog details"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(15,"No Blogs found for given Id"), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/getBlogComment/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComment(@PathVariable("blogId") int blogId){
		
		List<BlogComment> commentList = blogCommentService.getAllBlogComments(blogId);
		
		if(commentList!=null){
			
				return new ResponseEntity<List<BlogComment>>(commentList, HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(17,"Retrieving Blog Comment details failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/addBlogComment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		System.out.println("Blog comment = "+blogComment.getBlogId()+" user Id; "+blogComment.getUserId() );
		UsersDetails user = (UsersDetails) session.getAttribute("user");
		if(user.isIsonline()){
			blogComment.setCommentDate(new Date());
			blogComment.setUserName(user.getFirstName()+" "+user.getLastName());
			if(blogCommentService.addBlogComment(blogComment)){
				return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
			}else{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(19,"Blog Comment Addition failed"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<ErrorClazz>(new ErrorClazz(12,"User must be logged in to add a comment"),HttpStatus.CONFLICT);
	}
	
	
	@RequestMapping(value="/updateLikes",method=RequestMethod.POST)
	public ResponseEntity<?> updateNoOfLikes(@RequestBody Blog blog,HttpSession session){
		
		UsersDetails user = (UsersDetails) session.getAttribute("user");
		if(user.isIsonline()){			
			if(blogService.updateBlog(blog)){
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}else{
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(11,"Blog updation failed"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<ErrorClazz>(new ErrorClazz(12,"User must be logged in to like"),HttpStatus.CONFLICT);
	}
	
	
	@RequestMapping(value="/updateBlog",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog,HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		 if(userId==null)
		    {
		    	return new ResponseEntity<ErrorClazz>(new ErrorClazz(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
			}	   
		    else	
		    {
				UsersDetails user = userService.viewUser(userId);
				
				if(user.isIsonline()){
					Blog tempBlog = blogService.getBlog(blog.getBlogId());
					tempBlog.setBlogTitle(blog.getBlogTitle());
					tempBlog.setBlogContent(blog.getBlogContent());
					if(blogService.updateBlog(tempBlog)){
						return new ResponseEntity<Blog>(blog, HttpStatus.OK);
					}else{
						return new ResponseEntity<ErrorClazz>(new ErrorClazz(21,"Blog Updation failed"), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(22,"User must be logged in to update blog"),HttpStatus.CONFLICT);
			}
	}
	
	@RequestMapping(value="/deleteBlogById/{blogId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteBlogById(@PathVariable("blogId") int blogId,HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		 if(userId==null)
		    {
		    	return new ResponseEntity<ErrorClazz>(new ErrorClazz(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
			}	   
		    else	
		    {
		    	UsersDetails user = userService.viewUser(userId);
		    	if(user.isIsonline()){
					Blog tempBlog = blogService.getBlog(blogId);
					if(blogService.deleteBlog(tempBlog)){
						return new ResponseEntity<Blog>(tempBlog, HttpStatus.OK);
					}else{
						return new ResponseEntity<ErrorClazz>(new ErrorClazz(23,"Blog Deletion failed"), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				return new ResponseEntity<ErrorClazz>(new ErrorClazz(24,"User must be logged in to delete blog"),HttpStatus.CONFLICT);
		    	
		    }
	}
}