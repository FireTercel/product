package com.clothes.dao;

import java.util.List;

import com.clothes.entity.Clothes;

public interface IClothesDAO {
	
	public boolean create(Clothes clothes);
	
	public Clothes queryClothes(String id);
	
	public int updateByID(Clothes clothes);
	
	public int deleteByID(Clothes clothes);
	
	public List<Clothes> findAll();
	
	public List<Clothes> findAll(String column,String keyWordString);
	
	public List<Clothes> findAll(int currentPage,int lineSize,String column,String keyWord);
	
	public int findCount();
	
	public int findCount(String column,String keyWord);

}
