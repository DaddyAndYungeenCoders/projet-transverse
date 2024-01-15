package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.SensorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SensorEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Coords coords;
    private int intensity;

    @Override
    public SensorDTO toDTO() {
        return new SensorDTO(this.id, this.coords, this.intensity);
    }
}
