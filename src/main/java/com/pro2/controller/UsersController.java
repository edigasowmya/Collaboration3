/*package com.pro2.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pro2.dao.UserDAO;
import com.pro2.model.UsersDetails;
import com.pro2.service.UsersService;

@RestController
public class UsersController {
@Autowired
private UserDAO userDao;

public UsersController(){
	System.out.println("UserController is Instantiated");
	private UsersService usersService;
	
	Logger Logger=LoggerFactory.getLogger(UsersController.class);

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UsersDetails user) {
		
	System.out.println("Controller started");
		if(!usersService.isUsernameValid(user.getUserName()))
		{
			Error error = new Error(user.getUserName()+"..username already exists,, please enter different username");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		
	 if (!usersService.isEmailValid(user.getEmail())) {
			Error error = new Error(user.getEmail()+"...Email address already exists,, please enter different email");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
	 System.out.println("Controller method invoked");
		boolean result = usersService.saveOrUpdate(user);
		if (result) {
			return new ResponseEntity<UsersDetails>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

*/























package com.pro2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pro2.dao.UserDAO;
import com.pro2.model.ErrorClazz;
import com.pro2.model.UsersDetails;
import com.pro2.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;

public UsersController(){
	System.out.println("UserController is Instantiated");
}
//client -Angular JS Client
//User - in JSON
//convert JSON to java object
// ? any type, for success Type is User, for error Type is ErrorClazz
@RequestMapping(value="/registeruser",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody UsersDetails user){//5
	try{
		if(!usersService.isUsernameValid(user.getUserName())){//Duplicate Username
			ErrorClazz error=new ErrorClazz(2,"UserName already exists..please choose different username");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		if(!usersService.isEmailValid(user.getEmail())){
			ErrorClazz error=new ErrorClazz(3,"EmailId already exists..please enter different email address");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		usersService.saveOrUpdate(user);
	}catch(Exception e){
		ErrorClazz error=new ErrorClazz(1,"Unable to register user details");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		//failure - response.data=error, response.status=500			
	}
	return new ResponseEntity<UsersDetails>(user,HttpStatus.OK);
}
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody UsersDetails user,HttpSession session){
	UsersDetails validUser=usersService.login(user);
	if(validUser==null){
		ErrorClazz errorClazz=new ErrorClazz(4,"Invalid username/password");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//ErrorClazz,401
	}
	else{
		validUser.setIsonline(true);
		session.setAttribute("username", validUser.getUserName());
		usersService.updateUser(validUser);
		return new ResponseEntity<UsersDetails>(validUser,HttpStatus.OK);
	}
}
@RequestMapping(value="/logout",method=RequestMethod.GET)
public ResponseEntity<?> logout(HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null){//without login
		ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401 -> login.html
	}
	UsersDetails user=usersService.getUser(username);
	user.setIsonline(false);
	usersService.updateUser(user);//update User set online=false where username=?
	session.removeAttribute("username");
	session.invalidate();
	
	return new ResponseEntity<UsersDetails>(user,HttpStatus.OK);
}
}

