package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = AdvancedJpaApplication.class)
class ReviewRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void retrieveCourseForReview(){
        Review review = reviewRepository.findById(50001);
        logger.info("Course for given review {}", review.getCourse());
    }

}