package com.simulator.webserver.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserEntity.class)
@Table(name = "Users")
public class UserEntity implements Serializable {
    @Id
    private int id;
    private String username;
    private String password;
    private Date created_date;
}
