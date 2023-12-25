package com.simulator.webserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "FireEvent")
public class FireEventEntity {
    @Id
    private String id;
    private Coords coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;
}
