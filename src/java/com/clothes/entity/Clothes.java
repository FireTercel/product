package com.clothes.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clothes", schema = "")
public class Clothes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6443081108568908754L;

	public String id;

	public String cloth_name;

	public double price;

	public String description;

	public String commant;

	public Date upload_time;

	public String usable;
	
	public Set clothimage=new HashSet(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCloth_name() {
		return cloth_name;
	}

	public void setCloth_name(String cloth_name) {
		this.cloth_name = cloth_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCommant() {
		return commant;
	}

	public void setCommant(String commant) {
		this.commant = commant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public Set getClothimage() {
		return clothimage;
	}

	public void setClothimage(Set clothimage) {
		this.clothimage = clothimage;
	}

	public String toString() {
		String string = "id:" + id + " cloth_name:" + cloth_name + " price:"
				+ price + " description:" + description + " commant:" + commant
				+ " upload_time:" + upload_time + " usable:" + usable;
		return string;
	}

}
