package com.example.apptamlinh.ChiemTinhFeature;


import java.util.List;

public class ChiemTinhModel {
    private List<Constellation> constellations;

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public void setConstellations(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    public static class Constellation {
        private String ten;
        private GenderAttributes nam;
        private GenderAttributes nu;

        // Getters và setters
        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public GenderAttributes getNam() {
            return nam;
        }

        public void setNam(GenderAttributes nam) {
            this.nam = nam;
        }

        public GenderAttributes getNu() {
            return nu;
        }

        public void setNu(GenderAttributes nu) {
            this.nu = nu;
        }
    }

    public static class GenderAttributes {
        private String tongquan;
        private String tinhcach;
        private String diemmanh;
        private String diemyeu;
        private String giadinh;
        private String tinhyeu;
        private String tinhduc;
        private String sunghiep;

        // Getters và setters
        public String getTongquan() {
            return tongquan;
        }

        public void setTongquan(String tongquan) {
            this.tongquan = tongquan;
        }

        public String getTinhcach() {
            return tinhcach;
        }

        public void setTinhcach(String tinhcach) {
            this.tinhcach = tinhcach;
        }

        public String getDiemmanh() {
            return diemmanh;
        }

        public void setDiemmanh(String diemmanh) {
            this.diemmanh = diemmanh;
        }

        public String getDiemyeu() {
            return diemyeu;
        }

        public void setDiemyeu(String diemyeu) {
            this.diemyeu = diemyeu;
        }

        public String getGiadinh() {
            return giadinh;
        }

        public void setGiadinh(String giadinh) {
            this.giadinh = giadinh;
        }

        public String getTinhyeu() {
            return tinhyeu;
        }

        public void setTinhyeu(String tinhyeu) {
            this.tinhyeu = tinhyeu;
        }

        public String getTinhduc() {
            return tinhduc;
        }

        public void setTinhduc(String tinhduc) {
            this.tinhduc = tinhduc;
        }

        public String getSunghiep() {
            return sunghiep;
        }

        public void setSunghiep(String sunghiep) {
            this.sunghiep = sunghiep;
        }
    }
}