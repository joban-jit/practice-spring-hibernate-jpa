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
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = AdvancedJpaApplication.class)
public class NativeQueryTest {

    // select * from CourseDetails - native query
    // select c from Course - jpql query

    // one scenario where we would use "Native query" over "JPQL query" is when we have to
    // do mass update, as in JPQL it will take a row then update it and take another row and update it
    // so you can't mass update
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    void native_query(){
        Query nativeQuery = em.createNativeQuery("select * from course_details where is_deleted = false", Course.class);
        List resultList = nativeQuery.getResultList();
        logger.info("select * from course_details: {}", resultList);
    }

    @Test
    void native_query_with_where(){
        Query nativeQuery = em.createNativeQuery("select * from course_details where id = ?", Course.class);
        nativeQuery.setParameter(1, 10001);
        List resultList = nativeQuery.getResultList();
        logger.info("select * from course_details where id = ?: {}", resultList);
    }

    @Test
    void native_query_with_named_parameter(){
        Query nativeQuery = em.createNativeQuery("select * from course_details where id = :id", Course.class);
        nativeQuery.setParameter("id", 10001);
        List resultList = nativeQuery.getResultList();
        logger.info("select * from course_details where id = :id {}", resultList);
    }


    @Test
    @Transactional
    // as we are trying to make changes using native query
    void native_query_mass_update(){
        Query nativeQuery = em.createNativeQuery("update course_details set last_updated_date=current_timestamp()", Course.class);
        int updatedRows = nativeQuery.executeUpdate();
        logger.info("Number of rows updated: {}", updatedRows);
    }

}
