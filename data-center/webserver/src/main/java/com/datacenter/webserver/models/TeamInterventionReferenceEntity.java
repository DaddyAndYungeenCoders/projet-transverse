package com.datacenter.webserver.models;


import com.datacenter.webserver.dto.InterventionReferenceDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "team_intervention_reference")
public class TeamInterventionReferenceEntity extends BaseEntity {
    @Id
    Long id_fire_event;
    Long id_team;
    int duration;

    @Override
    public InterventionReferenceDTO toDTO() {
        return new InterventionReferenceDTO(this.id_fire_event, this.id_team, this.duration);
    }
}
