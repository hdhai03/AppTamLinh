package com.example.apptamlinh.CongDongFeature;

public class PostModel {
    String postID, postName, postNgaySinh, postGioiTinh, postCauHoi, postChiTiet, postImgUrl1, postImgUrl2, postImgUrl3, postImgUrl4, postUserID;
    long postTime;


    public PostModel() {
    }

    public PostModel(String postID, String postName, String postNgaySinh, String postGioiTinh, String postCauHoi, String postChiTiet, String postImgUrl1, String postImgUrl2, String postImgUrl3, String postImgUrl4, String postUserID, long postTime) {
        this.postID = postID;
        this.postName = postName;
        this.postNgaySinh = postNgaySinh;
        this.postGioiTinh = postGioiTinh;
        this.postCauHoi = postCauHoi;
        this.postChiTiet = postChiTiet;
        this.postImgUrl1 = postImgUrl1;
        this.postImgUrl2 = postImgUrl2;
        this.postImgUrl3 = postImgUrl3;
        this.postImgUrl4 = postImgUrl4;
        this.postUserID = postUserID;
        this.postTime = postTime;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostNgaySinh() {
        return postNgaySinh;
    }

    public void setPostNgaySinh(String postNgaySinh) {
        this.postNgaySinh = postNgaySinh;
    }

    public String getPostGioiTinh() {
        return postGioiTinh;
    }

    public void setPostGioiTinh(String postGioiTinh) {
        this.postGioiTinh = postGioiTinh;
    }

    public String getPostCauHoi() {
        return postCauHoi;
    }

    public void setPostCauHoi(String postCauHoi) {
        this.postCauHoi = postCauHoi;
    }

    public String getPostChiTiet() {
        return postChiTiet;
    }

    public void setPostChiTiet(String postChiTiet) {
        this.postChiTiet = postChiTiet;
    }

    public String getPostImgUrl1() {
        return postImgUrl1;
    }

    public void setPostImgUrl1(String postImgUrl1) {
        this.postImgUrl1 = postImgUrl1;
    }

    public String getPostImgUrl2() {
        return postImgUrl2;
    }

    public void setPostImgUrl2(String postImgUrl2) {
        this.postImgUrl2 = postImgUrl2;
    }

    public String getPostImgUrl3() {
        return postImgUrl3;
    }

    public void setPostImgUrl3(String postImgUrl3) {
        this.postImgUrl3 = postImgUrl3;
    }

    public String getPostImgUrl4() {
        return postImgUrl4;
    }

    public void setPostImgUrl4(String postImgUrl4) {
        this.postImgUrl4 = postImgUrl4;
    }

    public String getPostUserID() {
        return postUserID;
    }

    public void setPostUserID(String postUserID) {
        this.postUserID = postUserID;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }
}
