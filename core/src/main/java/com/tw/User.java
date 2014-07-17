package com.tw;

import javax.persistence.*;

/**
 * Created by twer on 7/16/14.
 */
@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private final long id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="USEREMAIL")
    private String userEmail;

    @Column(name="USEAGE")
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
