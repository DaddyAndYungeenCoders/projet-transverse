package com.simulator.webserver.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sensor")
public class SensorEntity implements Serializable {

    @Id
    private Long id;
    @Embedded
    private Coords coordsEntity;
}
