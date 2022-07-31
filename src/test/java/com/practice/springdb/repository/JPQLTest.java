package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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


}
