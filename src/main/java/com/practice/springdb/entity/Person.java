package com.practice.springdb.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private int id;
    private String name;
    private String location;
    private LocalDate birthDate;

}
