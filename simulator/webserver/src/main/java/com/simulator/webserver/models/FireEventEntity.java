package com.simulator.webserver.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FireEventEntity {
    private String id;
    private CoordsEntity coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;
}
