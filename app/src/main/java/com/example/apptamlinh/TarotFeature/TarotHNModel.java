package com.example.apptamlinh.TarotFeature;

public class TarotHNModel {
    private String name;
    private String number;
    private String arcana;
    private String suit;
    private String img;
    private String meaning;

    // Constructor
    public TarotHNModel(String name, String number, String arcana, String suit, String img, String meaning) {
        this.name = name;
        this.number = number;
        this.arcana = arcana;
        this.suit = suit;
        this.img = img;
        this.meaning = meaning;
    }

    public TarotHNModel() {
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArcana() {
        return arcana;
    }

    public void setArcana(String arcana) {
        this.arcana = arcana;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

