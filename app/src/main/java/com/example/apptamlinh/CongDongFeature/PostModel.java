package com.example.apptamlinh.CongDongFeature;

public class PostModel {
    String postTime;
    String postDetail;
    String postImg1, postImg2, postImg3, postImg4;

    public PostModel(String postTime, String postDetail, String postImg1, String postImg2, String postImg3, String postImg4) {
        this.postTime = postTime;
        this.postDetail = postDetail;
        this.postImg1 = postImg1;
        this.postImg2 = postImg2;
        this.postImg3 = postImg3;
        this.postImg4 = postImg4;
    }

    public PostModel() {
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public String getPostImg1() {
        return postImg1;
    }

    public void setPostImg1(String postImg1) {
        this.postImg1 = postImg1;
    }

    public String getPostImg2() {
        return postImg2;
    }

    public void setPostImg2(String postImg2) {
        this.postImg2 = postImg2;
    }

    public String getPostImg3() {
        return postImg3;
    }

    public void setPostImg3(String postImg3) {
        this.postImg3 = postImg3;
    }

    public String getPostImg4() {
        return postImg4;
    }

    public void setPostImg4(String postImg4) {
        this.postImg4 = postImg4;
    }
}
