package com.practice.springdb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString(exclude = {"id", "course"})
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private int id;

    private String rating;
    private String description;
    // Many Reviews are part of One course
    @ManyToOne
    // By default for ManyToOne FetchType is EAGER
    private Course course;

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }


}
