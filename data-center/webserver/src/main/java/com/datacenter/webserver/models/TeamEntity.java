package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.TeamDTO;
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
@Table(name = "team")
public class TeamEntity extends BaseEntity {
    @Id Long id;
    String chief_officer;
    int stamina;
    Long id_firestation;
    double current_latitude;
    double current_longitude;
    private boolean is_available;

    @Override
    public TeamDTO toDTO() {
        return new TeamDTO(this.id, this.chief_officer, this.stamina, this.id_firestation, new Coords(this.current_latitude, this.current_longitude), this.is_available);
    }
}
