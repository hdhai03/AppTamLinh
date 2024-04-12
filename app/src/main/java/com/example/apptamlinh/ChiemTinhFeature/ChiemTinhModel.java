package com.example.apptamlinh.ChiemTinhFeature;

public class ChiemTinhModel {
    String chiemTinhName;
    String chiemTinhTongQuan;
    String chiemTinhTinhCach;
    String chiemTinhDiemManh;
    String chiemTinhDiemYeu;
    String chiemTinhGiaDinh;
    String chiemTinhTinhYeu;
    String chiemTinhTinhDuc;
    String chiemTinhSuNghiep;
    int chiemTinhImages;

    public ChiemTinhModel(String chiemTinhName, String chiemTinhTongQuan, String chiemTinhTinhCach,
                          String chiemTinhDiemManh, String chiemTinhDiemYeu, String chiemTinhGiaDinh,
                          String chiemTinhTinhYeu, String chiemTinhTinhDuc, String chiemTinhSuNghiep,
                          int chiemTinhImages) {
        this.chiemTinhName = chiemTinhName;
        this.chiemTinhTongQuan = chiemTinhTongQuan;
        this.chiemTinhTinhCach = chiemTinhTinhCach;
        this.chiemTinhDiemManh = chiemTinhDiemManh;
        this.chiemTinhDiemYeu = chiemTinhDiemYeu;
        this.chiemTinhGiaDinh = chiemTinhGiaDinh;
        this.chiemTinhTinhYeu = chiemTinhTinhYeu;
        this.chiemTinhTinhDuc = chiemTinhTinhDuc;
        this.chiemTinhSuNghiep = chiemTinhSuNghiep;
        this.chiemTinhImages = chiemTinhImages;
    }

    public String getChiemTinhName() {
        return chiemTinhName;
    }

    public String getChiemTinhTongQuan() {
        return chiemTinhTongQuan;
    }

    public String getChiemTinhTinhCach() {
        return chiemTinhTinhCach;
    }

    public String getChiemTinhDiemManh() {
        return chiemTinhDiemManh;
    }

    public String getChiemTinhDiemYeu() {
        return chiemTinhDiemYeu;
    }

    public String getChiemTinhGiaDinh() {
        return chiemTinhGiaDinh;
    }

    public String getChiemTinhTinhYeu() {
        return chiemTinhTinhYeu;
    }

    public String getChiemTinhTinhDuc() {
        return chiemTinhTinhDuc;
    }

    public String getChiemTinhSuNghiep() {
        return chiemTinhSuNghiep;
    }

    public int getChiemTinhImages() {
        return chiemTinhImages;
    }
}
