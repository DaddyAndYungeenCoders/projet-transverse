package com.simulator.webserver.models;

import com.simulator.webserver.dto.FireStationDTO;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "firestation")
public class FireStationEntity extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Coords coords;

    public static FireStationDTO toDTO(FireStationEntity entity) {
        FireStationDTO dto = new FireStationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setCoords(entity.getCoords());
        return dto;
    }
}
