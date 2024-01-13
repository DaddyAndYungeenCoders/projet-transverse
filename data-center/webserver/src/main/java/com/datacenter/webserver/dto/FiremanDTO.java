package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.FiremanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiremanDTO extends BaseDTO implements Serializable {
    Long id;
    String lastname;
    String firstname;
    Date birthdate;
    int fireMastery;
    int idTeam;

    @Override
    public FiremanEntity toEntity() {
        return new FiremanEntity(this.id, this.lastname, this.firstname, this.birthdate, this.fireMastery, this.idTeam);
    }
}
