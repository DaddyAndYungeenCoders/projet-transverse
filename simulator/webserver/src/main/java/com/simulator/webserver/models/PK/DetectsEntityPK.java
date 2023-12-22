package com.simulator.webserver.models.PK;


import java.io.Serializable;
import java.util.Objects;

import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectsEntityPK implements Serializable {
    private SensorEntity sensorEntity;
    private FireEventEntity fireEventEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetectsEntityPK that = (DetectsEntityPK) o;
        return Objects.equals(sensorEntity, that.sensorEntity) &&
               Objects.equals(fireEventEntity, that.fireEventEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorEntity, fireEventEntity);
    }
}
