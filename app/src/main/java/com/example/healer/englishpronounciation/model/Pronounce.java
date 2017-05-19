package com.example.healer.englishpronounciation.model;

public class Pronounce {

	private String imageSymbol;
	private String imagePronounce;
	private String description;
	private String sound;
	private int id;
	private int topicId;

	public Pronounce(int id, String imageSymbol, String imagePronounce, String sound, String description, int topicId) {
		this.imageSymbol = imageSymbol;
		this.imagePronounce = imagePronounce;
		this.description = description;
		this.sound = sound;
		this.id = id;
		this.topicId = topicId;
	}

	public String getImageSymbol() {
		return imageSymbol;
	}

	public void setImageSymbol(String imageSymbol) {
		this.imageSymbol = imageSymbol;
	}

	public String getImagePronounce() {
		return imagePronounce;
	}

	public void setImagePronounce(String imagePronounce) {
		this.imagePronounce = imagePronounce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
}
