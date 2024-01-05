package com.simulator.models.PK;


import com.simulator.models.FireEventEntity;
import com.simulator.models.SensorEntity;

import java.io.Serializable;
import java.util.Objects;

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
