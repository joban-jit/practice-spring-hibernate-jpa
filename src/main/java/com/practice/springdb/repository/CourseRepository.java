package com.practice.springdb.repository;

import com.practice.springdb.entities.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    EntityManager em;

    public Course findById(int id){
        return em.find(Course.class, id);
    }
    // to insert and update

    // now here we are making change to the data
    // so you need to do in transactional manner = sequence of steps and all steps should be successful
    //  so that's why we used @transaction annotation

    @Transactional
    public Course save(Course course){
       if(0 == course.getId()){
           em.persist(course); // inserting a new course
       }else{
           em.merge(course); // updating a existing course
       }
       return course;

    }

    @Transactional
    public void deleteById(int id){
        Course course = findById(id);
        Optional.ofNullable(course).ifPresentOrElse(c->{
                    em.remove(c);
                    logger.info("Course with {} id has been deleted", id);
                },
                ()-> logger.info("Given course doesn't exist")
        );
    }

}
