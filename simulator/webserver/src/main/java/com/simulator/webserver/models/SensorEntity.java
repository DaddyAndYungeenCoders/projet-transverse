package com.simulator.webserver.models;

import java.io.Serializable;

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
@IdClass(SensorEntity.class)
@Table(name = "Sensor")
public class SensorEntity implements Serializable{
    
    @Id
    private String id;
    private CoordsEntity coordsEntity;
}
