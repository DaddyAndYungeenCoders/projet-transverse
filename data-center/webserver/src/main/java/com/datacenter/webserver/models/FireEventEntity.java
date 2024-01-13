package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;
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
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;

    @Override
    public FireEventDTO toDTO() {
        return new FireEventDTO(this.getId(), this.getCoords(), this.getReal_intensity(), this.getStart_date(), this.getEnd_date(), this.is_real());
    }
}
