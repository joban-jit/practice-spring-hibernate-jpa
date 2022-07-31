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

    @Transactional
    public void playWithEntityManager1(){
        logger.info("Play with Entity Manager: Start");
        Course course = new Course("Work with Database");
        em.persist(course);
        // IMP: now below change will also reflect in database
        // Why? Because we are making change to data in table in this method so we need to add Transactional
        // to this method/or class.
        // now whenever you have added a transactional and you are managing this with entity manager
        // so any update/insert/delete will be continuously be managed by entity manager until the end of
        // transaction.
        // so all the changes like below are being tracked even though we haven't actually explicitly saved it
        // as all those changes are tracked by entity manager
        // so below change will reflect in database
        course.setName("Work with AWS");


    }

    @Transactional
    public void playWithEntityManager2(){
        Course course1 = new Course("Work with pcf app");
        em.persist(course1);
        Course course2 = new Course("Work with ecs app");
        em.persist(course2);
        em.flush(); // so what it does is that changes that are done before this , will be sent out to database
        // so it will create both records in database for course1 and course2

        em.detach(course2);// here we are using this detach method to say that changes to course2 after this point
        // will not be tracked by entity manager
        course2.setName("Work with ecs app - updated"); // so this course2 change will not go to database
        em.flush();
        course1.setName("Work with pcf app - updated"); // but since we haven't deattched the course1, so this change
        // will still show in database
        em.flush();

    }
    @Transactional
    public void playWithEntityManager3(){
        Course course1 = new Course("Work with pcf app");
        em.persist(course1);
        Course course2 = new Course("Work with ecs app");
        em.persist(course2);
        em.flush(); // so what it does is that changes that are done before this , will be sent out to database

        em.clear();// this will clear the entity manager after this point
        // so changes will not be tracked by entity manager
        // so all below changes won't be tracked
        course2.setName("Work with ecs app - updated");
        em.flush();
        course1.setName("Work with pcf app - updated");
        em.flush();

    }
    @Transactional
    public void playWithEntityManager4(){
        Course course1 = new Course("Work with pcf app");
        em.persist(course1);
        Course course2 = new Course("Work with ecs app");
        em.persist(course2);
        em.flush(); // so what it does is that changes that are done before this , will be sent out to database


        course1.setName("Work with pcf app - updated");
        course2.setName("Work with ecs app - updated");
        // to refresh the course1 changes with the one in database, so like setting the course1
        // to previous value as in database
        em.refresh(course1);// this will replace all the changes we have done after doing persist in this example
        // course1 value would be "Work with pcf app" not "Work with pcf app - updated"
        // as this course1 will be fetched from database and then updated this variable with it

        em.flush();

    }

}
