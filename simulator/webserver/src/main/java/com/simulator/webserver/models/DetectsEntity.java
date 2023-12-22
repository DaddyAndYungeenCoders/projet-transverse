package com.simulator.webserver.models;

import java.io.Serializable;

import com.simulator.webserver.dto.DetectsDTO;
import com.simulator.webserver.models.PK.DetectsEntityPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DetectsEntityPK.class)
@Table (name = "detects")
public class DetectsEntity {
    @Id
    private SensorEntity sensorEntity;
    @Id
    private FireEventEntity fireEventEntity;
    private float intensity;

    public DetectsDTO toDTO(){
        return new DetectsDTO(this.sensorEntity.getId(), this.intensity);
    }    
}

