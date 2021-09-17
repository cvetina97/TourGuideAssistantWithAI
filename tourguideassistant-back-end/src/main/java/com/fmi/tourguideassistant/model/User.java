package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String uid;
    private String email;

    public String getUid(){
        return uid;
    }

    public void setId(String uid){
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public User(String uid, String email){
        this.uid = uid;
        this.email = email;
    }

    public User(String email)
    {
        this(null, email);
    }

    public User(){

    }

}
