package com.example.apptamlinh;

public class UserModel implements Comparable<UserModel> {
    String userID, userName;
    long userScore;

    public UserModel(String userID, String userName, long userScore) {
        this.userID = userID;
        this.userName = userName;
        this.userScore = userScore;
    }

    public UserModel() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserScore() {
        return userScore;
    }

    public void setUserScore(long userScore) {
        this.userScore = userScore;
    }

    @Override
    public int compareTo(UserModel other) {
        // So sánh userScore của 2 đối tượng
        return Long.compare(this.userScore, other.userScore);
    }
}
