package com.avra.es_cqrs.repository;

import com.avra.es_cqrs.cqrs.User;

import java.util.HashMap;
import java.util.Map;

public class UserWriteRepository {
    private Map<String, User> store = new HashMap<>();

    public void addUser(String userid, User user) {
        store.put(userid, user);
    }

    public User getUser(String userId) {
        return store.get(userId);
    }
}
