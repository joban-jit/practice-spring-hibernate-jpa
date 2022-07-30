package com.practice.springdb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Course(String name) {
        this.name = name;
    }
}
