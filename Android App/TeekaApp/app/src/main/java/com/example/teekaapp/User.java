package com.example.teekaapp;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String userId;
    public String username;
    public String email;
    public String fatname;
    public String mobno;
    public String dob;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userId, String username, String email, String fatname, String mobno, String dob) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fatname = fatname;
        this.mobno = mobno;
        this.dob = dob;
    }

    public String getuserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatname() {
        return fatname;
    }

    public void setFatname(String fatname) {
        this.fatname = fatname;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}