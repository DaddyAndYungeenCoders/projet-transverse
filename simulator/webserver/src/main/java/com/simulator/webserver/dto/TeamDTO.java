package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import com.simulator.webserver.models.TeamEntity;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long fireStationId;
    private int stamina;
    private int fireMastery;

    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.fireStationId, this.stamina, this.fireMastery);
    }
}
