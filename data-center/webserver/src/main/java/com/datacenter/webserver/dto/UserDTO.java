package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private Long id;
    private String username;
    private String password;
    private Date createdDate;

    @Override
    public UserEntity toEntity() {
        return new UserEntity(this.id, this.username, this.password, this.createdDate);
    }
}
