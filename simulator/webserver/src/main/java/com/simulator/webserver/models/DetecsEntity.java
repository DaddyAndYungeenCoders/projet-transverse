package com.simulator.webserver.models;

import java.io.Serializable;

import com.simulator.webserver.dto.DetecsDTO;
import com.simulator.webserver.models.PK.DetecsEntityPK;
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
@IdClass(DetecsEntityPK.class)
@Table (name = "detects")
public class DetecsEntity {
    @Id
    private SensorEntity sensorEntity;
    @Id
    private FireEventEntity fireEventEntity;
    private float intensity;

    public DetecsDTO toDTO(){
        return new DetecsDTO(this.sensorEntity.getId(), this.intensity);
    }    
}

