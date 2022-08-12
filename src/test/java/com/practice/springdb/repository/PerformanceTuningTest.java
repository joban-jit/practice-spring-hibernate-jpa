package com.practice.springdb.repository;


import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = AdvancedJpaApplication.class)
public class PerformanceTuningTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void creatingNPlusOneProblem(){
        //let's say we have 3 courses
        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        courses.forEach(course->logger.info("Couse: {} : Students: {}", course, course.getStudents()));
        // here to get the course - it runs 1 query - it returns 3 courses
        //but to get the students for each course - it need to run individual query for each course - so total 3 queries

        // problem is that if our first query return 1000 courses and to get the students we need to fire 1000 queries so total 1001 queries
        // so thus N+1 problem
        // solution:
        // 1. we can avoid this by using EAGER fetch,(NOT RECOMMENDED) so carefully evaluate it as it will return all the students as well everytime
        // 2. using EntityGraph solution, we still be using LAZY fetch
        // 3. using JoinFetch, we still be using LAZY fetch,
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_EntityGraph(){
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");
        // so above line translate to : we want to run both Course and students together
        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach(course->logger.info("Couse: {} : Students: {}", course, course.getStudents()));
        // only one statement query using left outer join

    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_JoinFetch(){
        //query_get_all_courses_join_fetch we defined this query in course entity class
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        courses.forEach(course->logger.info("Couse: {} : Students: {}", course, course.getStudents()));
        // only one statement query using inner join

    }
}
