package com.pro2.dao;

import java.util.List;

import com.pro2.model.UsersDetails;

public interface UserDAO {
	
	public boolean saveOrUpdate(UsersDetails users);
	public UsersDetails updateUser(UsersDetails users);
	public void delete(UsersDetails user);
	public UsersDetails getUser(String username);
	public UsersDetails viewUser(int userid);
	public List<UsersDetails> UserList();
	public UsersDetails login(UsersDetails user);
	public boolean isUsernameValid(String username);
	public boolean isEmailValid(String email);
	public boolean deleteUser(int id);
	public List<UsersDetails> getAllUsers();



}