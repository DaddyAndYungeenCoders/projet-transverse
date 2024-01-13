package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.SensorEntity;
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
    private int intensity;

    public static SensorEntity toEntity(SensorDTO sensorDTO) {
        SensorEntity entity = new SensorEntity();
        entity.setId(sensorDTO.getId());
        entity.setCoords(sensorDTO.getCoords());
        entity.setIntensity(sensorDTO.getIntensity());
        return entity;
    }

    @Override
    public SensorEntity toEntity() {
        return new SensorEntity(this.id, this.coords, this.intensity);
    }
}
