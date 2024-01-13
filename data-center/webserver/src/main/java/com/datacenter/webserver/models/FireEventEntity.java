package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.FireEventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "validation_status")
    private ValidationStatus validation_status;
    
    @Override
    public FireEventDTO toDTO() {
        return new FireEventDTO(this.id, this.coords, this.intensity, this.start_date, this.end_date, this.validation_status);
    }
}
