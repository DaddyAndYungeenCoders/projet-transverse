package com.simulator.webserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntity {
    
    private String id;
    private CoordsEntity coordsEntity;
}
