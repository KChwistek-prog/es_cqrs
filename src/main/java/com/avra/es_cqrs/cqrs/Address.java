package com.avra.es_cqrs.cqrs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String city;
    private String state;
    private String postcode;

}
