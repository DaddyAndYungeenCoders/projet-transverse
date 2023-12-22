package com.simulator.webserver.models;

import java.io.Serializable;

import com.simulator.webserver.dto.DetecsDTO;

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
@IdClass(DetecsEntity.class)
@Table (name = "defects")
public class DetecsEntity {
    @Id
    private SensorEntity sensorEntity;
    @Id
    private FireEventEntity fireEventEntity;
    private float intensity;

    public DetecsDTO toDTO(){
        return new DetecsDTO(this.sensorEntity.getId(), this.intensity);
    }    

    private class DetecsEntityPK implements Serializable {
    private SensorEntity sensorEntity;
    private FireEventEntity fireEventEntity;
}
}

