package com.practice.springdb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private int id;

    private String rating;
    private String description;

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }


}
