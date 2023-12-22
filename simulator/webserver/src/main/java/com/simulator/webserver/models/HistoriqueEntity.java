package com.simulator.webserver.models;

import java.util.Date;
import com.simulator.webserver.models.PK.HistoriqueEntityPK;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(HistoriqueEntityPK.class)
@Table(name = "Historique")
public class HistoriqueEntity {
    @Id
    private UserEntity user;
    @Id
    private FireEventEntity fire_event;
    private Date created_date;
}

