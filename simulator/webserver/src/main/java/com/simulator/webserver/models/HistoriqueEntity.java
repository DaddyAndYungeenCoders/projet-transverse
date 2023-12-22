package com.simulator.webserver.models;

import java.io.Serializable;
import java.util.Date;

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
@IdClass(HistoriqueEntity.class)
@Table(name = "Historique")
public class HistoriqueEntity {
    @Id
    private UserEntity user;
    @Id
    private FireEventEntity fire_event;
    private Date created_date;

    private class HistoriqueEntityPK implements Serializable {
    private UserEntity user;
    private FireEventEntity fire_event;
}
}

