package com.avra.es_cqrs.cqrs;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> store = new HashMap<>();

    public void addUser(String userId, User user) {
        store.put(userId,user);
    }

    public User getUser(String userId) {
        return store.get(userId);
    }
}
