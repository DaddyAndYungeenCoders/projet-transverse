package com.simulator.webserver.models.PK;


import java.io.Serializable;
import java.util.Objects;

import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DetectsEntityPK implements Serializable {
    private Long sensorId;
    private Long fireEventId;
}
