package com.simulator.webserver.models;

import com.simulator.webserver.dto.TeamDTO;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "team")
public class TeamEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fire_station_id;
    private int stamina;
    private int fire_mastery;

    public TeamDTO toDTO() {
        return new TeamDTO(this.id, this.fire_station_id, this.stamina, this.fire_mastery);
    }
}
