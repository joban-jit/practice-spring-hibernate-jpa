package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = AdvancedJpaApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository courseSpringDataRepository;

    @Test
    void findById_course_present(){
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001);
        courseOptional.ifPresentOrElse(
                course->logger.info("Course: {}", course),
                ()->logger.info("Course not found")
        );
    }

    @Test
    void findById_not_course_present(){
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001);
        courseOptional.ifPresentOrElse(
                course->logger.info("Course: {}", course),
                ()->logger.info("Course not found")
        );
    }

    @Test
    void other_inbuilt_methods(){
        Course course = new Course("Kafka in 100 steps");
        // creating a new course
        courseSpringDataRepository.save(course);
        // updating a course
        course.setName("Kafka in 100 steps - updated");
        courseSpringDataRepository.save(course);

        logger.info("All Courses: {}", courseSpringDataRepository.findAll());
        logger.info("Courses count: {}", courseSpringDataRepository.count());

        // sorting by name in descending order
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Sorted Courses: {}", courseSpringDataRepository.findAll(sort));
    }
    @Test
    void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        logger.info("First page: {}",firstPage.getContent() );
        Pageable secondPageRequest = firstPage.nextPageable();
        Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageRequest);
        logger.info("Second page: {}", secondPage.getContent());
        // and so on for next pages..
    }

    @Test
    void custom_methods(){
        List<Course> byNameList = courseSpringDataRepository.findByName("JPA in 50 Steps");
        logger.info("Find By Name: {}", byNameList);

    }


}
