package com.simulator.webserver.models;

import java.util.Date;


public class UserEntity {
    private int id;
    private String username;
    private String password;
    private Date created_date;

    public UserEntity(int id, String username){
        this.id = id;
        this.username = username;
    }

    public UserEntity(int id, String username, String password, Date created_date){
        //this = UserEntity(id, username);
        this.password = password;
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }



}
