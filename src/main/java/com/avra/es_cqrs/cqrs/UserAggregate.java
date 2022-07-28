package com.avra.es_cqrs.cqrs;

import com.avra.es_cqrs.es.*;
import com.avra.es_cqrs.es.events.UserContactAddedEvent;
import com.avra.es_cqrs.es.events.UserContactRemovedEvent;
import com.avra.es_cqrs.es.events.UserCreatedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserAggregate {
    private EventStore writeRepository;
    public UserAggregate(EventStore repository) {
        this.writeRepository = repository;
    }

    public List<Event> handleCreateUserCommand(CreateUserCommand command) {
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(),
                command.getFirstName(), command.getLastName());
        writeRepository.addEvent(command.getUserId(), event);
        return Arrays.asList(event);
    }

    public List<Event> handleUpdateUserCommand(UpdateUserCommand command) {
        User user = UserUtility.recreateUserState(writeRepository, command.getUserId());
        List<Event> events = new ArrayList<>();

        List<Contact> contactsToRemove = user.getContacts().stream()
                .filter(c -> !command.getContacts().contains(c))
                .collect(Collectors.toList());
        for (Contact contact : contactsToRemove) {
            UserContactRemovedEvent contactRemovedEvent = new UserContactRemovedEvent(contact.getType(),
                    contact.getDetail());
            events.add(contactRemovedEvent);
            writeRepository.addEvent(command.getUserId(), contactRemovedEvent);
        }
        List<Contact> contactsToAdd = command.getContacts().stream()
                .filter(c -> !user.getContacts().contains(c))
                .collect(Collectors.toList());
        for (Contact contact : contactsToAdd) {
            UserContactAddedEvent contactAddedEvent = new UserContactAddedEvent(contact.getType(),
                    contact.getDetail());
            events.add(contactAddedEvent);
            writeRepository.addEvent(command.getUserId(), contactAddedEvent);
        }

        // similarly process addressesToRemove
        // similarly process addressesToAdd

        return events;
    }
}
