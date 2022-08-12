package com.practice.springdb.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

// to embed this into another entity (and not make it as another entity and use it with relationship)
// we use
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private String line1;
    private String line2;
    private String city;
}
