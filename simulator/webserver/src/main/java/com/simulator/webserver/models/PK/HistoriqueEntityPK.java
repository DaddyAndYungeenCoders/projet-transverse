package com.simulator.webserver.models.PK;

import java.io.Serializable;

import com.simulator.webserver.models.FireEventEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueEntityPK implements Serializable{
    private UserEntity user;
    private FireEventEntity fire_event;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((fire_event == null) ? 0 : fire_event.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistoriqueEntityPK other = (HistoriqueEntityPK) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (fire_event == null) {
            if (other.fire_event != null)
                return false;
        } else if (!fire_event.equals(other.fire_event))
            return false;
        return true;
    }
}
