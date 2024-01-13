package com.simulator.dto;

import com.simulator.models.CoordsEntity;
import com.simulator.models.SensorEntity;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorDTO extends BaseDTO implements Serializable {
    private Long id;
    private int intensity;
    private CoordsEntity coords;

    public static SensorEntity toEntity(SensorDTO sensorDTO) {
        SensorEntity entity = new SensorEntity();
        entity.setId(sensorDTO.getId());
        entity.setIntensity(sensorDTO.getIntensity());
        entity.setCoordsEntity(sensorDTO.getCoords());
        return entity;
    }
}
