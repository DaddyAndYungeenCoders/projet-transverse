package com.simulator.webserver.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueEntity {
    private UserEntity user;
    private FireEventEntity fire_event;
    private Date created_date;
}
