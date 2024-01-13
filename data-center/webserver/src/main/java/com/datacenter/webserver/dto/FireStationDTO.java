package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.FireStationEntity;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FireStationDTO extends BaseDTO implements Serializable {
    Long id;
    String name;
    String address;
    Coords coords;

    public static FireStationEntity toEntity(FireStationDTO dto) {
        FireStationEntity entity = new FireStationEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setCoords(dto.getCoords());
        return entity;
    }

    @Override
    public FireStationEntity toEntity() {
        return new FireStationEntity(this.id, this.name, this.address, this.coords);
    }
}
