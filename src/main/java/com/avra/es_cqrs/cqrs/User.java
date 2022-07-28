package com.avra.es_cqrs.cqrs;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    private String userid;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    private Set<Contact> contacts = new HashSet<>();
    private Set<Address> addresses = new HashSet<>();

}


