package com.simulator.webserver.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CoordsEntity.class)
@Table (name = "Coords")
public class CoordsEntity {

    @Id
    float latitude;
    @Id
    float longitude;    

    private class CoordsEntityPK implements Serializable {
    float latitude;
    float longitude;  
}
}

