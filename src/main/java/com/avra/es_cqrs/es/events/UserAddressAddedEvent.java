package com.avra.es_cqrs.es.events;

import com.avra.es_cqrs.es.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserAddressAddedEvent extends Event {
    private String city;
    private String state;
    private String postCode;
}