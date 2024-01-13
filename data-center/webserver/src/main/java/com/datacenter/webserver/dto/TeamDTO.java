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
    private Long id;
    private String chiefOfficer;
    private int stamina;
    private Long idFirestation;
    private boolean isAvailable;
    
    @Override
    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.chiefOfficer, this.stamina, this.idFirestation, this.isAvailable);
    }
}
