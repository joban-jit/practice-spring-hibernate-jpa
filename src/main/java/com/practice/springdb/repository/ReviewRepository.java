package com.practice.springdb.repository;

import com.practice.springdb.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReviewRepository {

    @Autowired
    private EntityManager em;

    public Review findById(int id){
        return em.find(Review.class, id);
    }
}
