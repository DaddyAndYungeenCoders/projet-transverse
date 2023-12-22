package com.simulator.webserver.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sensor")
public class SensorEntity implements Serializable{
    
    @Id
    private Long id;
    private Coords coordsEntity;
}
