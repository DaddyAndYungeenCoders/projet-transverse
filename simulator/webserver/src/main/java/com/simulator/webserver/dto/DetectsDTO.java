package com.simulator.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectsDTO {
    private Long idSensor;
    private Long idFireEvent;
    private float intensity;
}
