package com.simulator.webserver.models;

import java.io.Serializable;

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
@Table(name = "Sensor")
public class SensorEntity implements Serializable {

    @Id
    private Long id;
    @Embedded
    private CoordsEntity coordsEntity;
}
