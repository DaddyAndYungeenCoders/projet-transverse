package com.datacenter.webserver.models.PK;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HistoriqueEntityPK implements Serializable{
    private Long sensor_id;
    private Long fire_event_id;
}
