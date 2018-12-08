package com.julianmartinez.avoris.repository;

import com.julianmartinez.avoris.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public long addUser(String name){
        String query = "INSERT INTO USER (NAME) VALUES (?)";
        return jdbcTemplate.update(query, name);
    }

    public List<User> getUserList(){
        String query = "SELECT * FROM USER";
        return jdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(User.class));
    }

    public @Nullable User getUserById(long id){
        try {
            String query = "SELECT * FROM USER WHERE ID = ?";
            return jdbcTemplate.queryForObject(query,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void modifyUserById(long id, String name){
        String query = "UPDATE USER SET NAME = ? WHERE ID = ?";
        jdbcTemplate.update(query, name, id);
    }

    public void removeUserById(long id){
        String query = "DELETE FROM USER WHERE ID = ?";
        jdbcTemplate.update(query, id);
    }
}
