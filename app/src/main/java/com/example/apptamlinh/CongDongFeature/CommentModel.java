package com.example.apptamlinh.CongDongFeature;

public class CommentModel {
    String commentID, commentDetail, commentUserID, commentPostID;
    long commentLikeCount;

    public CommentModel(String commentDetail, String commentUserID, String commentPostID, long commentTime, long commentLikeCount) {
        this.commentDetail = commentDetail;
        this.commentUserID = commentUserID;
        this.commentPostID = commentPostID;
        this.commentLikeCount = commentLikeCount;
    }

    public CommentModel() {
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public String getCommentUserID() {
        return commentUserID;
    }

    public void setCommentUserID(String commentUserID) {
        this.commentUserID = commentUserID;
    }

    public String getCommentPostID() {
        return commentPostID;
    }

    public void setCommentPostID(String commentPostID) {
        this.commentPostID = commentPostID;
    }

    public long getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(long commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }
}
