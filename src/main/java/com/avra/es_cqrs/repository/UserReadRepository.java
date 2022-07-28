package com.avra.es_cqrs.repository;

import com.avra.es_cqrs.cqrs.UserAddress;
import com.avra.es_cqrs.cqrs.UserContact;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class UserReadRepository {
    private Map<String, UserAddress> userAddress = new HashMap<>();
    private Map<String, UserContact> userContact = new HashMap<>();

    public UserContact getUserContact(String id) {
        return userContact.get(id);
    }

    public UserAddress getUserAddress(String id) {
        return userAddress.get(id);
    }

    public void addUserAddress(String id, UserAddress address) {
        userAddress.put(id, address);
    }

    public void addUserContact(String id, UserContact contact) {
        userContact.put(id,contact);
    }
}
