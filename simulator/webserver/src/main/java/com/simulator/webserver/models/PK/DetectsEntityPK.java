package com.simulator.webserver.models.PK;


import java.io.Serializable;
import java.util.Objects;

import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DetectsEntityPK implements Serializable {
    @Column(name = "id_sensor", nullable = false)
    private Long idSensor;
    @Column(name = "id_fire_event", nullable = false)
    private Long idFireEvent;
}
