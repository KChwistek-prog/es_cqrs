package com.avra.es_cqrs.es;

import com.avra.es_cqrs.cqrs.Address;
import com.avra.es_cqrs.cqrs.Contact;
import com.avra.es_cqrs.cqrs.User;
import com.avra.es_cqrs.cqrs.UserUtility;
import com.avra.es_cqrs.es.events.*;

import java.util.Set;
import java.util.stream.Collectors;

public class UserService {
    private EventStore repository;
    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        repository.addEvent(userId, new UserCreatedEvent(userId, firstName, lastName));
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) {
        User user = UserUtility.recreateUserState(repository, userId);
        user.getContacts().stream()
          .filter(c -> !contacts.contains(c))
          .forEach(c -> repository.addEvent(
            userId, new UserContactRemovedEvent(c.getType(), c.getDetail())));
        contacts.stream()
          .filter(c -> !user.getContacts().contains(c))
          .forEach(c -> repository.addEvent(
            userId, new UserContactAddedEvent(c.getType(), c.getDetail())));
        user.getAddresses().stream()
          .filter(a -> !addresses.contains(a))
          .forEach(a -> repository.addEvent(
            userId, new UserAddressRemovedEvent(a.getCity(), a.getState(), a.getPostcode())));
        addresses.stream()
          .filter(a -> !user.getAddresses().contains(a))
          .forEach(a -> repository.addEvent(
            userId, new UserAddressAddedEvent(a.getCity(), a.getState(), a.getPostcode())));
    }

    public Set<Contact> getContactByType(String userId, String contactType) {
        User user = UserUtility.recreateUserState(repository, userId);
        return user.getContacts().stream()
          .filter(c -> c.getType().equals(contactType))
          .collect(Collectors.toSet());
    }

    public Set<Address> getAddressByRegion(String userId, String state) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        return user.getAddresses().stream()
          .filter(a -> a.getState().equals(state))
          .collect(Collectors.toSet());
    }
}