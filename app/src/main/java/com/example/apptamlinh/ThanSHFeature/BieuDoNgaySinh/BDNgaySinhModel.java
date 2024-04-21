package com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh;

public class BDNgaySinhModel {
    private String mot;
    private String hai;
    private String ba;
    private String bon;
    private String nam;
    private String luuy;

    public BDNgaySinhModel(String mot, String hai, String ba, String bon, String nam, String luuy) {
        this.mot = mot;
        this.hai = hai;
        this.ba = ba;
        this.bon = bon;
        this.nam = nam;
        this.luuy = luuy;
    }

    public BDNgaySinhModel() {
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getHai() {
        return hai;
    }

    public void setHai(String hai) {
        this.hai = hai;
    }

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }

    public String getBon() {
        return bon;
    }

    public void setBon(String bon) {
        this.bon = bon;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getLuuy() {
        return luuy;
    }

    public void setLuuy(String luuy) {
        this.luuy = luuy;
    }
}
