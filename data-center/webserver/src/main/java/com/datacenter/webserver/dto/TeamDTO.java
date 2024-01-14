package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.models.Coords;
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
    private Coords coords;
    private boolean isAvailable;

    public static TeamEntity toEntity(TeamDTO dto) {
        return new TeamEntity(dto.getId(), dto.getChiefOfficer(), dto.getStamina(), dto.getIdFirestation(), dto.getCoords().getLatitude(), dto.getCoords().getLongitude(), dto.isAvailable());
    }
    
    @Override
    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.chiefOfficer, this.stamina, this.idFirestation, this.coords.getLatitude(), this.coords.getLongitude(), this.isAvailable);
    }
}
