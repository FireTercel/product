package com.clothes.entity;

import java.io.Serializable;

public class ClothImage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7988863133950668510L;

	public String id;
	
	
	public String image_url;
	
	private Clothes clothes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
	public Clothes getClothes() {
		return clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public String toString(){
		String string="id:"+id+" image_url:"+image_url;
		
		return string;
	}
	
	

}
