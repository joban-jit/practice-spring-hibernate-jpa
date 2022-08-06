package com.practice.springdb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"id", "student"})
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String number;

    // we have used "mappedBy" to make Student as owning side of relationship
    // now even we don't get any column in Passport table regarding "student" but it will give us way to navigate
    // from passport to student if we want to
    // so this is called Bi-Directional relationship -
    // Student 1--1 Passport and Passport 1--1 Student , in this case Student is owning side
    @OneToOne(fetch=FetchType.LAZY, mappedBy = "passport") // "passport" is the variable name in Student class
    private Student student;

    public Passport(String number){
        this.number = number;
    }
    // in to string method we can't add student in it as we are not loading student with it, when we fetching the passport
    // as we have student is the owning side
}
