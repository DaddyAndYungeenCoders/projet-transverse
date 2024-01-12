package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import com.simulator.webserver.models.SensorEntity;
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
    private Coords coords;
    private Integer intensity;

    public static SensorEntity toEntity(SensorDTO sensorDTO) {
        SensorEntity entity = new SensorEntity();
        entity.setId(sensorDTO.getId());
        entity.setCoords(sensorDTO.getCoords());
        entity.setIntensity(sensorDTO.getIntensity());
        return entity;
    }
}
