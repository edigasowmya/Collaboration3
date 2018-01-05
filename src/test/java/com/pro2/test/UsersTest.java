package com.pro2.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pro2.dao.UserDAO;
import com.pro2.model.UsersDetails;



@Ignore
public class UsersTest {
	

	private static UsersDetails user;
	
	@Autowired
	private static UserDAO userDAO;
	
	@SuppressWarnings("resource")
	
	
	@BeforeClass
	public static void initialize(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.pro2.*");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	
		//user = (UsersDetails) context.getBean("user");
		
	}

	@Test
	public void createUsersTest(){
		
		
		
		
		
			
			UsersDetails user = new UsersDetails();
			
			user.setFirstName("sowmya");
			user.setLastName("goud");
			user.setUserName("sowmya");
			user.setEmail("sowmya@gmail.com");
			user.setPassword("sowmya3");
			
			user.setContact("9505856599");
			user.setRole("STUDENT");
			user.setEnable(true);
			user.setIsonline(false);
			
			assertTrue(userDAO.saveOrUpdate(user));
	}
	
	
	

	@Test
	public void updateUserTest(){
		UsersDetails user = new UsersDetails();
		user.setUser_id(1);
		user.setFirstName("sowmya");
		user.setLastName("ediga");
		user.setUserName("sowmyagoud");
		user.setEmail("sowmya@gmail.com");
		user.setPassword("sowmya3");
		user.setContact("9505856599");
		user.setRole("STUDENT");
		user.setEnable(true);
		user.setIsonline(false);
		
		assertTrue(userDAO.saveOrUpdate(user));
		
		
	}
	
	@Test
	public void deleteUserTest(){

		UsersDetails user = new UsersDetails();
		user.setUser_id(1);
		assertTrue(userDAO.deleteUser(1));

	}
	@Test
	public void retrieveUserTest(){
		UsersDetails user =userDAO.getUser("sowmya");
	assertNotNull("Problem in retrieving user details",user);
	System.out.println(" firstName : "+user.getFirstName()+" lastName :  "+user.getLastName()+"\n email : "+user.getEmail()+"  role : "+user.getRole());
		
	}
	

	@Test
	public void retrieveAllUsersTest(){
		
		List<UsersDetails> userList =userDAO.getAllUsers();
		assertNotNull("Problem in retrieving user details",userList);
		showUserList(userList);
	}
		
	

	private void showUserList(List<UsersDetails> userList) {
	
		for (UsersDetails usersDetails : userList) {
			
			System.out.println("User Id: "+usersDetails.getUser_id());
			System.out.println("User First Name: "+usersDetails.getFirstName());
			System.out.println("User LastName: "+usersDetails.getLastName());
			System.out.println("User Email Id: "+usersDetails.getEmail());
			System.out.println("User UserName: "+usersDetails.getUserName());
			System.out.println("User contact: "+usersDetails.getContact());
			System.out.println("User Role: "+usersDetails.getRole());
			System.out.println();
		
		
		}}
}
	
	

