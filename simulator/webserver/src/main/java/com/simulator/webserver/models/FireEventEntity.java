package com.simulator.webserver.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "FireEvent")
public class FireEventEntity implements Serializable{
    @Id
    private Long id;
    @Embedded
    private CoordsEntity coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;
}
