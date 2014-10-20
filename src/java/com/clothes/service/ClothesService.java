package com.clothes.service;

import java.io.Serializable;

import com.core.common.service.CommonService;

public interface ClothesService extends CommonService{
	
	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);

}
