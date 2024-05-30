package com.lorminel.service;

import com.lorminel.domain.User;

public interface Session {

    User unwrap();

    class EmptySession implements Session {
        @Override
        public User unwrap() {
            return null;
        }
    }
}
