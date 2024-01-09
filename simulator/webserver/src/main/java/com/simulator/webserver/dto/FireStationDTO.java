package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class FireStationDTO implements Serializable {
    Long id;
    String name;
    String address;
    Coords coords;
}
