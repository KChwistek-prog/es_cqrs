package com.avra.es_cqrs.es.events;

import com.avra.es_cqrs.es.Event;
import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserAddressRemovedEvent extends Event {
    private String city;
    private String state;
    private String postCode;


}