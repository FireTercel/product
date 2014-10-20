package com.clothes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clothes.service.ClothesService;
import com.core.common.service.impl.CommonServiceImpl;

@Service("clothesService")
@Transactional
public class ClothesServiceImpl extends CommonServiceImpl implements ClothesService{
	
	

}
