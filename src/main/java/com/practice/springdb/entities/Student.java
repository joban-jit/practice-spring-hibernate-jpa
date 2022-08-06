package com.practice.springdb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter

@ToString(exclude = "id")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch=FetchType.LAZY) // by default, it is FetchType.EAGER
    private Passport passport;

    public Student(String name){
        this.name = name;
    }
}
