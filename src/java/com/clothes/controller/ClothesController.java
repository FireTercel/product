package com.clothes.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clothes.entity.Clothes;
import com.clothes.service.ClothesService;
import com.clothes.service.impl.ClothesServiceImpl;

@Controller
@RequestMapping("/clothesController")
public class ClothesController {
	
	private static final Logger logger=Logger.getLogger(ClothesController.class);
	
	@Resource(name="clothesService")
	private ClothesService clothesService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params = "clothes")
	public String toMainPage(){
		
		return "main/index";
	}
	


}
