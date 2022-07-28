package com.avra.es_cqrs.cqrs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {

    private String type;
    private String detail;

}
