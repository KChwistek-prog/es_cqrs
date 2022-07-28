package com.avra.es_cqrs.cqrs;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class UserContact {
    private Map<String, Set<Contact>> contactByType = new HashMap<>();

}
