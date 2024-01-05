package com.simulator.repository;

import com.simulator.models.UserEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final BddService bddService = new BddService();

    public UserEntity getUserById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM user WHERE id = " + id);
        return buildUserEntity(resultSet);
    }

    public List<UserEntity> getAllUsers() {
        ResultSet resultSet = bddService.Query("SELECT * FROM user");
        return buildUserEntities(resultSet);
    }

    public UserEntity getUserByUsername(String username) {
        ResultSet resultSet = bddService.Query("SELECT * FROM user WHERE username = '" + username + "'");
        return buildUserEntity(resultSet);
    }

    public UserEntity addUser(UserEntity userEntity) {
        bddService.Query("INSERT INTO user (username, password, created_date) VALUES (?, ?, ?)",
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getCreated_date());
        return userEntity;
    }

    public UserEntity updateUser(UserEntity userEntity) {
        bddService.Query("UPDATE user SET username = ?, password = ?, created_date = ? WHERE id = ?",
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getCreated_date(),
                userEntity.getId());
        return userEntity;
    }

    public void deleteUser(UserEntity userEntity) {
        bddService.Query("DELETE FROM user WHERE id = ?", userEntity.getId());
    }

    private List<UserEntity> buildUserEntities(ResultSet resultSet) {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getLong("id"));
                userEntity.setUsername(resultSet.getString("username"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setCreated_date(resultSet.getDate("created_date"));
                userEntities.add(userEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntities;
    }

    private UserEntity buildUserEntity(ResultSet resultSet) {
        UserEntity userEntity = new UserEntity();
        try {
            while (resultSet.next()) {
                userEntity.setId(resultSet.getLong("id"));
                userEntity.setUsername(resultSet.getString("username"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setCreated_date(resultSet.getDate("created_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntity;
    }
}
