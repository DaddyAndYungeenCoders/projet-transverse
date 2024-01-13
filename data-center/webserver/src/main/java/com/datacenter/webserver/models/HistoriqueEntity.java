package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.HistoriqueDTO;
import com.datacenter.webserver.models.PK.HistoriqueEntityPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(HistoriqueEntityPK.class)
@Table(name = "historique")
public class HistoriqueEntity extends BaseEntity {
    @Id
    private Long sensor_id;
    @Id
    private Long fire_event_id;
    private Date created_date;

    @Override
    public HistoriqueDTO toDTO() {
        return new HistoriqueDTO(this.sensor_id, this.fire_event_id, this.created_date);
    }
}

