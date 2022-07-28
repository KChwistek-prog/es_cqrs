package com.avra.es_cqrs.es.events;

import com.avra.es_cqrs.es.Event;
import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserContactAddedEvent extends Event {
    private String contactType;
    private String contactDetails;


}