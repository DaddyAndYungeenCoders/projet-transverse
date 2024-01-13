package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.TeamInterventionReferenceEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InterventionReferenceDTO extends BaseDTO {
    Long idFireEvent;
    Long idTeam;
    int duration;

    @Override
    public TeamInterventionReferenceEntity toEntity() {
        return new TeamInterventionReferenceEntity(this.idFireEvent, this.idTeam, this.duration);
    }
}
