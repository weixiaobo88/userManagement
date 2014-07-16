package com.tw;

/**
 * Created by twer on 7/16/14.
 */
public class User {
    private final long id;
    private String userName;
    private String userEmail;
    private int userAge;

    public User(long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
