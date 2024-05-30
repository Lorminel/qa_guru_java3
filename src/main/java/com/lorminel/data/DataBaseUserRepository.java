package com.lorminel.data;

import com.lorminel.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class DataBaseUserRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(
            DataSourceProvider.INSTANCE.getDataSource()
    );

    @Override
    public Optional<User> findByUsername(String username) {

        try {
            String password = jdbcTemplate.queryForObject("select password from users where username = ?",
                    new String[]{username}, String.class);
            return Optional.of(
                    new User(
                            username,
                            password
                    )
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
