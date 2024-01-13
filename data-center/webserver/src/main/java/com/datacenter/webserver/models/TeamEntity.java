package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;
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
    private boolean is_available;

    @Override
    public TeamDTO toDTO() {
        return new TeamDTO(this.id, this.chief_officer, this.stamina, this.id_firestation, this.is_available);
    }
}
