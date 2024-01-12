package com.simulator.webserver.models;

import java.io.Serializable;

import com.simulator.webserver.dto.SensorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor")
public class SensorEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Coords coords;
    private Integer intensity;

    public static SensorDTO toDTO(SensorEntity sensorEntity) {
        SensorDTO dto = new SensorDTO();
        dto.setId(sensorEntity.getId());
        dto.setCoords(sensorEntity.getCoords());
        dto.setIntensity(sensorEntity.getIntensity());
        return dto;
    }
}
