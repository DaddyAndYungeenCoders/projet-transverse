package com.simulator.webserver.models;

import com.simulator.webserver.dto.DetectsDTO;
import com.simulator.webserver.models.PK.DetectsEntityPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DetectsEntityPK.class)
@Table (name = "detects")
public class DetectsEntity {
    @Id
    @Column(name = "id_sensor", nullable = false)
    private Long idSensor;
    @Id
    @Column(name = "id_fire_event", nullable = false)
    private Long idFireEvent;
    private float intensity;

    public DetectsDTO toDTO(){
        return new DetectsDTO(this.idSensor, this.idFireEvent, this.intensity);
    }    
}

