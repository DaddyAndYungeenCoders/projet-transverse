package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.FiremanDTO;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "fireman")
public class FiremanEntity extends BaseEntity {
    @Id
    Long id;
    String lastname;
    String firstname;
    Date birthdate;
    int fire_mastery;
    int id_team;

    @Override
    public FiremanDTO toDTO() {
        return new FiremanDTO(this.id, this.lastname, this.firstname, this.birthdate, this.fire_mastery, this.id_team);
    }
}
