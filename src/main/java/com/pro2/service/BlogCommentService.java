package com.pro2.service;

import java.util.List;

import com.pro2.model.BlogComment;

public interface BlogCommentService {

	public boolean addBlogComment(BlogComment comment);
	
	public boolean updateBlogComment(BlogComment comment);
	
	public boolean deleteBlogComment(BlogComment comment);
	
	public BlogComment getBlogComment(int blogCommentId);
	
	public List<BlogComment> getAllBlogComments(int blogId);
}