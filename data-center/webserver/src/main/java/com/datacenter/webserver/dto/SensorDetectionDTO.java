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
public class SensorDetectionDTO extends BaseDTO implements Serializable {
    private Long id;
    private int intensity;
    private boolean isReal;

    @Override
    public BaseEntity toEntity() {
        return null;
    }

//    public static SensorDetectionEntity toEntity(SensorDetectionDTO sensorDTO) {
//        SensorEntity entity = new SensorEntity();
//        entity.setId(sensorDTO.getId());
//        entity.setIntensity(sensorDTO.getIntensity());
//        return entity;
//    }


}
