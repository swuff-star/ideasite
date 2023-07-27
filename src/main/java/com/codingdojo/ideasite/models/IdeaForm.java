package com.codingdojo.ideasite.models;

import org.springframework.web.multipart.MultipartFile;

public class IdeaForm {
	
	private String name;
	private String desc;
	private MultipartFile imageFile;
	    
	public MultipartFile getImageFile() {
	    return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
	    this.imageFile = imageFile;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}

