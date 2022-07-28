package com.avra.es_cqrs.cqrs;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UpdateUserCommand {
    private String userId;
    private Set<Address> addresses;
    private Set<Contact> contacts;
}
