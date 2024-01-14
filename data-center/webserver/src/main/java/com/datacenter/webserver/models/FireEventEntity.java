package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.FireEventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "fireevent")
public class FireEventEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Coords coords;
    private int intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_verified;
    private Long sensorId;
    private Long id_equipe_intervention;
    private boolean is_handled;
    
    @Override
    public FireEventDTO toDTO() {
        return new FireEventDTO(this.id, this.coords, this.intensity, this.start_date, this.end_date, this.is_verified, this.sensorId, this.id_equipe_intervention, this.is_handled);
    }
}
