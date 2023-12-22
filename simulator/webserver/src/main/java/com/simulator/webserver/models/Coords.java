package com.simulator.webserver.models;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coords implements Serializable{

    private double latitude;
    private double longitude;

}

