package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorDTO implements Serializable {
    private Long id;
    private Coords coords;
}
