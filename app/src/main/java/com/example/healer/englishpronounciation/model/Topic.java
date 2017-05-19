package com.example.healer.englishpronounciation.model;

public class Topic {

	private String icon;
	private String title;
	private String signature;
	private int id;
	public Topic(int id, String title, String signature) {
		super();
		this.id = id;
		this.title = title;
		this.signature = signature;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
	
}
