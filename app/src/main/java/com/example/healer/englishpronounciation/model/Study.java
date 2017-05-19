package com.example.healer.englishpronounciation.model;

/**
 * Created by Healer on 13-May-17.
 */

public class Study {
    private int id;
    private String word;
    private String mean;
    private String sound;
    private String pronounce;
    private int pronounceId;
    private String record;


    public Study(int id, String word, String mean, String sound, String pronounce, int pronounceId) {
        this.id = id;
        this.word = word;
        this.mean = mean;
        this.sound = sound;
        this.pronounce = pronounce;
        this.pronounceId = pronounceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public int getPronounceId() {
        return pronounceId;
    }

    public void setPronounceId(int pronounceId) {
        this.pronounceId = pronounceId;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
