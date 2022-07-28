package com.avra.es_cqrs.cqrs;

import com.avra.es_cqrs.es.*;
import com.avra.es_cqrs.es.events.UserAddressAddedEvent;
import com.avra.es_cqrs.es.events.UserAddressRemovedEvent;
import com.avra.es_cqrs.es.events.UserContactAddedEvent;
import com.avra.es_cqrs.es.events.UserContactRemovedEvent;
import com.avra.es_cqrs.repository.UserReadRepository;

import java.util.*;
public class UserProjector {
    UserReadRepository readRepository = new UserReadRepository();
    public UserProjector(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public void project(String userId, List<Event> events) {
        for (Event event : events) {
            if (event instanceof UserAddressAddedEvent)
                apply(userId, (UserAddressAddedEvent) event);
            if (event instanceof UserAddressRemovedEvent)
                apply(userId, (UserAddressRemovedEvent) event);
            if (event instanceof UserContactAddedEvent)
                apply(userId, (UserContactAddedEvent) event);
            if (event instanceof UserContactRemovedEvent)
                apply(userId, (UserContactRemovedEvent) event);
        }
    }

    public void apply(String userId, UserAddressAddedEvent event) {
        Address address = new Address(
                event.getCity(), event.getState(), event.getPostCode());
        UserAddress userAddress = Optional.ofNullable(
                        readRepository.getUserAddress(userId))
                .orElse(new UserAddress());
        Set<Address> addresses = Optional.ofNullable(userAddress.getAddressByRegion()
                        .get(address.getState()))
                .orElse(new HashSet<>());
        addresses.add(address);
        userAddress.getAddressByRegion()
                .put(address.getState(), addresses);
        readRepository.addUserAddress(userId, userAddress);
    }

    public void apply(String userId, UserAddressRemovedEvent event) {
        Address address = new Address(
                event.getCity(), event.getState(), event.getPostCode());
        UserAddress userAddress = readRepository.getUserAddress(userId);
        if (userAddress != null) {
            Set<Address> addresses = userAddress.getAddressByRegion()
                    .get(address.getState());
            if (addresses != null)
                addresses.remove(address);
            readRepository.addUserAddress(userId, userAddress);
        }
    }

    public void apply(String userId, UserContactAddedEvent event) {
        Contact contact = new Contact(event.getContactType(), event.getContactDetails());
        UserContact userContact = Optional.ofNullable(readRepository.getUserContact(userId))
                .orElse(new UserContact());
        Set<Contact> contacts = Optional.ofNullable(userContact.getContactByType()
                        .get(contact.getType()))
                .orElse(new HashSet<>());
        contacts.add(contact);
        userContact.getContactByType()
                .put(contact.getType(), contacts);
        readRepository.addUserContact(userId, userContact);
    }

    public void apply(String userId, UserContactRemovedEvent event) {
        Contact contact = new Contact(event.getContactType(), event.getContactDetails());
        UserContact userContact = readRepository.getUserContact(userId);
        if (userContact != null) {
            Set<Contact> contacts = userContact.getContactByType()
                    .get(contact.getType());
            if (contacts != null)
                contacts.remove(contact);
            readRepository.addUserContact(userId, userContact);
        }
    }
}


//public class UserProjector {
//    UserReadRepository readRepository = new UserReadRepository();
//
//    public UserProjector(UserReadRepository readRepository) {
//        this.readRepository = readRepository;
//    }
//
//    public void project(User user) {
//        UserContact userContact = Optional.ofNullable(
//                        readRepository.getUserContact(user.getUserid()))
//                .orElse(new UserContact());
//        Map<String, Set<Contact>> contactByType = new HashMap<>();
//        for (Contact contact : user.getContacts()) {
//            Set<Contact> contacts = Optional.ofNullable(
//                            contactByType.get(contact.getType()))
//                    .orElse(new HashSet<>());
//            contacts.add(contact);
//            contactByType.put(contact.getType(), contacts);
//        }
//        userContact.setContactByType(contactByType);
//        readRepository.addUserContact(user.getUserid(), userContact);
//
//        UserAddress userAddress = Optional.ofNullable(
//                        readRepository.getUserAddress(user.getUserid()))
//                .orElse(new UserAddress());
//        Map<String, Set<Address>> addressByRegion = new HashMap<>();
//        for (Address address : user.getAddresses()) {
//            Set<Address> addresses = Optional.ofNullable(
//                            addressByRegion.get(address.getState()))
//                    .orElse(new HashSet<>());
//            addresses.add(address);
//            addressByRegion.put(address.getState(), addresses);
//        }
//        userAddress.setAddressByRegion(addressByRegion);
//        readRepository.addUserAddress(user.getUserid(), userAddress);
//    }
//}
