package com.avra.es_cqrs.cqrs;

import lombok.Getter;

@Getter
public class AddressByRegionQuery {
    private String userId;
    private String state;
}