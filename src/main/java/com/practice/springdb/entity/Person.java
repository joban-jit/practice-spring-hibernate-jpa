package com.practice.springdb.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Person")
@NamedQuery(name="find_all_person", query="select p from Person p") // Person is the Entity not DB table
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String location;
    @Column(name="birth_date")
    private LocalDate birthDate;

    public Person(String name, String location, LocalDate birthDate){
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

}
