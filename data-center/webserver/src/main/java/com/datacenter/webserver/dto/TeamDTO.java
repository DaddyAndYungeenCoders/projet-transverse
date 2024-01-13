package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.models.TeamEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDTO extends BaseDTO {
    Long id;
    String chiefOfficer;
    int stamina;
    Long id_firestation;
    
    @Override
    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.chiefOfficer, this.stamina, this.id_firestation);
    }
}
