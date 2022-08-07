package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import com.practice.springdb.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes= AdvancedJpaApplication.class)
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void findAll_jpql_basic(){
        Query selectQuery = em.createQuery("Select c From Course c");
        List resultList = selectQuery.getResultList();
        logger.info("Select c From Course c: {}", resultList);

    }

    @Test
    void findAll_jpql_typed(){
        TypedQuery<Course> selectQuery = em.createQuery("Select c From Course c", Course.class);
        List<Course> resultList = selectQuery.getResultList();
        logger.info("Select c From Course c: {}", resultList);
    }

    @Test
    void findAll_jpql_typed_where(){
        TypedQuery<Course> selectQuery = em.createQuery("Select c From Course c where name like 'Work%'", Course.class);
        List<Course> resultList = selectQuery.getResultList();
        logger.info("Select c From Course c: {}", resultList);
    }
    @Test
    void findAll_jpql_named_query_where(){
        Query selectQuery = em.createNamedQuery("query_get_courses_with_like_keyword");
        List resultList = selectQuery.getResultList();
        logger.info("Select c From Course c: {}", resultList);
    }
    @Test
    void findAll_jpql_named_query(){
        Query selectQuery = em.createNamedQuery("query_get_all_courses");
        List resultList = selectQuery.getResultList();
        logger.info("Select c From Course c: {}", resultList);
    }

    @Test
    void jpql_courses_without_students(){
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where c.students is empty ", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        logger.info("Results: {}", resultList);
    }

    @Test
    void jpql_courses_with_atleast_2_students(){
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where size(c.students) >=2 ", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        logger.info("Results: {}", resultList);
    }

    @Test
    void jpql_courses_ordered_by_students(){
        TypedQuery<Course> typedQuery =  em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        logger.info("Results: {}", typedQuery.getResultList());
    }

    @Test
    @Transactional
    void jpql_students_with_passports_in_a_certain_pattern(){
        TypedQuery<Student> typedQuery =  em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
        logger.info("Results: {}", typedQuery.getResultList());
        //other useful keywords
        // between 100 and 1000
        // is null
        // uppper
        // lower
        // trim
        // length
    }

    @Test
    @Transactional
    void join(){
        // like inner join
        Query query = em.createQuery("Select c, s from Course c Join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size : {}", resultList.size());
        resultList.stream().forEach(result->{
            logger.info("Course : {} -- Student : {}", result[0],result[1]);
        });
    }
    @Test
    @Transactional
    void leftjoin(){
        // like left outer join
        Query query = em.createQuery("Select c, s from Course c Left Join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size : {}", resultList.size());
        resultList.stream().forEach(result->{
            logger.info("Course : {} -- Student : {}", result[0],result[1]);
        });
    }
    @Test
    @Transactional
    void crossjoin(){
        // here it return the results using permutation e.g. if Course table have 4 rows and Student table have 3
        // then the resultant number of records returned will be 4*3 = 12
        Query query = em.createQuery("Select c, s from Course c ,Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size : {}", resultList.size());
        resultList.stream().forEach(result->{
            logger.info("Course : {} -- Student : {}", result[0],result[1]);
        });
    }








}
