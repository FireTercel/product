package com.clothes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clothes.entity.Clothes;

@Controller
@RequestMapping("/cloth")
public class ClothController {
	
	private final static Map<String, Clothes> clothes=new HashMap<String, Clothes>();
	
	public ClothController(){
		
		
	}

}
