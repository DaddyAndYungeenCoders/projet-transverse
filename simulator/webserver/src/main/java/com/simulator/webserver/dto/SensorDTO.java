package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO implements Serializable {
    private Long id;
    private Coords coords;
}
