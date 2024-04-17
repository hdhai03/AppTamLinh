package com.example.apptamlinh.TarotFeature;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TarotTQModel implements Parcelable {
    private String name;
    private String number;
    private String arcana;
    private String suit;
    private String img;
    private String congviec;
    private String tinhyeu;
    private String taichinh;
    private String suckhoe;


    public TarotTQModel(String name, String number, String arcana, String suit, String img, String congviec, String tinhyeu, String taichinh, String suckhoe) {
        this.name = name;
        this.number = number;
        this.arcana = arcana;
        this.suit = suit;
        this.img = img;
        this.congviec = congviec;
        this.tinhyeu = tinhyeu;
        this.taichinh = taichinh;
        this.suckhoe = suckhoe;
    }

    public TarotTQModel() {
    }

    protected TarotTQModel(Parcel in) {
        name = in.readString();
        number = in.readString();
        arcana = in.readString();
        suit = in.readString();
        img = in.readString();
        congviec = in.readString();
        tinhyeu = in.readString();
        taichinh = in.readString();
        suckhoe = in.readString();
    }

    public static final Creator<TarotTQModel> CREATOR = new Creator<TarotTQModel>() {
        @Override
        public TarotTQModel createFromParcel(Parcel in) {
            return new TarotTQModel(in);
        }

        @Override
        public TarotTQModel[] newArray(int size) {
            return new TarotTQModel[size];
        }
    };

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

    public String getCongviec() {
        return congviec;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public String getTinhyeu() {
        return tinhyeu;
    }

    public void setTinhyeu(String tinhyeu) {
        this.tinhyeu = tinhyeu;
    }

    public String getTaichinh() {
        return taichinh;
    }

    public void setTaichinh(String taichinh) {
        this.taichinh = taichinh;
    }

    public String getSuckhoe() {
        return suckhoe;
    }

    public void setSuckhoe(String suckhoe) {
        this.suckhoe = suckhoe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(arcana);
        dest.writeString(suit);
        dest.writeString(img);
        dest.writeString(congviec);
        dest.writeString(tinhyeu);
        dest.writeString(taichinh);
        dest.writeString(suckhoe);
    }
}
