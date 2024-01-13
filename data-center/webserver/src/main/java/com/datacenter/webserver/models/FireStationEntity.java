package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.FireStationDTO;
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
    Long id;
    String name;
    String address;
    Coords coords;

    public FireStationDTO toDTO() {
        FireStationDTO dto = new FireStationDTO();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setAddress(this.getAddress());
        dto.setCoords(this.getCoords());
        return dto;
    }
}
