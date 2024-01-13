package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements Serializable {
    @Id
    private Long id;
    private String username;
    private String password;
    private Date created_date;

    @Override
    public UserDTO toDTO() {
        return new UserDTO(this.id, this.username, this.password, this.created_date);
    }
}
