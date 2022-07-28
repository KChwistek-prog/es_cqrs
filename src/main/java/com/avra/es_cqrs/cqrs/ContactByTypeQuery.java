package com.avra.es_cqrs.cqrs;

import lombok.Getter;

@Getter
public class ContactByTypeQuery {
    private String userId;
    private String contactType;

}

