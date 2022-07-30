package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// here we are launching the spring context in our unit test
// the context we want to launch is "SpringBootTest"
// which is our src/main/java AdvancedJpaApplication class
// so this below annotation will launch the AdvancedJpaApplication, like when we run the main class
// to launch our app, so as a result it will create our h2 database and create table, insert data into it
// run our command line code which we have mentioned in our main class as a part of "commandLineRunner"
@SpringBootTest(classes= AdvancedJpaApplication.class)
class CourseRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;
    @Test
    void findById_basic() {
        Course course = courseRepository.findById(10001);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    // as this test is changing the state of data we need to add "DirtiesContext"
    // so by adding this spring will automatically reset the data(actually it will kill the existing context
    // and run a new one for other tests after it complete running this test
    @Test
    @DirtiesContext
    void deleteById_basic() {
        courseRepository.deleteById(10001);
        assertNull(courseRepository.findById(10001));
    }

    @Test
    void save_basic(){
        Course course = courseRepository.findById(10001);
        assertEquals("JPA in 50 Steps", course.getName());
        // update the course
        course.setName("JPA in 50 Steps - Updated");
        courseRepository.save(course);
        Course courseUpdated = courseRepository.findById(10001);
        assertEquals("JPA in 50 Steps - Updated", courseUpdated.getName());

    }
}

// after it run the tests it will kill the context