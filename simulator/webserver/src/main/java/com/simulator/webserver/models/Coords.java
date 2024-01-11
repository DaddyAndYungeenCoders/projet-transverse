package com.simulator.webserver.models;

import java.io.Serializable;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coords implements Serializable{

    private double latitude;
    private double longitude;

}

