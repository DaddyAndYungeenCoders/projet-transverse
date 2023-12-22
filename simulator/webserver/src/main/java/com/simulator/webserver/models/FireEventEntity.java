package com.simulator.webserver.models;

import java.io.Serializable;
import java.sql.Date;

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
@IdClass(FireEventEntity.class)
@Table (name = "FireEvent")
public class FireEventEntity implements Serializable{
    @Id
    private String id;
    private CoordsEntity coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;
}
