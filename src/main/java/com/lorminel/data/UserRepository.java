package com.lorminel.data;

import com.lorminel.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    class MockUserRepository implements UserRepository {
        @Override
        public Optional<User> findByUsername(String username) {
            if("dasha".equals(username)){
                return Optional.of(
                        new User("dasha",
                                "12345")

                );
            } else {
                return Optional.empty();
            }

        }

    }
}
