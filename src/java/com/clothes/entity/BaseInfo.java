package com.clothes.entity;

import java.io.Serializable;

public class BaseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2424792548427017834L;

	private String id;
	
	private String title;
	
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		
		String string="id:"+id+" title:"+title+" content:"+content;
		return string;
		
	}

}
