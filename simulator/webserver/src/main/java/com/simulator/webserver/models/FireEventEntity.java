package com.simulator.webserver.models;

import java.io.Serializable;
import java.sql.Date;

import com.simulator.webserver.dto.FireEventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;
    
    public FireEventDTO toDTO() {
        return new FireEventDTO(this.id, this.coords, this.real_intensity, this.start_date, this.end_date, this.is_real);
    }
}
