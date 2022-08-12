package com.practice.springdb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"id", "course"})
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(value = EnumType.STRING) // to tell the JPA that this is enumerated
    // if we want to store the ONE as 1 in database, we use value = EnumType.ORDINAL
    // if we want to store the ONE as 'ONE' in database, we use value = EnumType.STRING
    private ReviewRating rating;
    private String description;
    // Many Reviews are part of One course
    @ManyToOne
    // By default for ManyToOne FetchType is EAGER
    private Course course;

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }


}
