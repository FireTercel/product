package com.clothes.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.clothes.dao.IUser;
import com.clothes.entity.User;

//public class UserDAOImpl extends HibernateDaoSupport implements IUser {
public class UserDAOImpl implements IUser {
	@Override
	public boolean create(User users) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User queryUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByID(User users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByID(User users) {
		
		
		
		return 0;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(String column, String keyWordString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(int currentPage, int lineSize, String column,
			String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findCount(String column, String keyWord) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args){
		
	}

}
