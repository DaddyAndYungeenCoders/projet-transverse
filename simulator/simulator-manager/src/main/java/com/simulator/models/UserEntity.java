package com.simulator.models;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Date created_date;

    public UserEntity(Long id, String username, String password, Date created_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_date = created_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
