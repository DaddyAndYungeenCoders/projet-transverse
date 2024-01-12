package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import com.simulator.webserver.models.FireStationEntity;
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
}
