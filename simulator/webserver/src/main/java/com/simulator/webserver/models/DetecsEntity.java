package com.simulator.webserver.models;

import com.simulator.webserver.dto.DetecsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetecsEntity {
    private SensorEntity sensorEntity;
    private FireEventEntity fireEventEntity;
    private float intensity;

    public DetecsDTO toDTO(){
        return new DetecsDTO(this.sensorEntity.getId(), this.intensity);
    }    
}
