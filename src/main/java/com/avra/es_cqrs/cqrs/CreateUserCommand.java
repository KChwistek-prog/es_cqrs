package com.avra.es_cqrs.cqrs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserCommand {
    private String userId;
    private String firstName;
    private String lastName;
}