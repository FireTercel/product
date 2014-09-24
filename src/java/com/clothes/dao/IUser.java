package com.clothes.dao;

import java.util.List;

import com.clothes.entity.User;


public interface IUser {
public boolean create(User users);
	
	public User queryUser(String id);
	
	public int updateByID(User users);
	
	public int deleteByID(User users);
	
	public List<User> findAll();
	
	public List<User> findAll(String column,String keyWordString);
	
	public List<User> findAll(int currentPage,int lineSize,String column,String keyWord);
	
	public int findCount();
	
	public int findCount(String column,String keyWord);

}
