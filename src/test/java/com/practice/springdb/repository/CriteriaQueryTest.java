package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes= AdvancedJpaApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void criteria_query(){
        // so let's use Java api to build query to replace = "Select c From Course c"

        // 1. Use CriteriaBuilder to create a Criteria query returning the expected result object.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for the tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
         // for this example we don't need this step

        //4. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = typedQuery.getResultList();
        logger.info("Typed Query: {}", resultList);
    }
}
