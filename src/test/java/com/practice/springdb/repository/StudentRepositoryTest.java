package com.practice.springdb.repository;

import com.practice.springdb.AdvancedJpaApplication;
import com.practice.springdb.entities.Passport;
import com.practice.springdb.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest(classes= AdvancedJpaApplication.class)
class StudentRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DirtiesContext
    void someTest(){
        studentRepository.understandingPersistenceContext();
    }


    @Test
    @DirtiesContext
    @Transactional // by adding this transaction will only end at end of test of this method
    void retrieveStudentWithPassport() {
        // if we don't add @Transactional , transaction will end only at below line
        Student student = em.find(Student.class, 20001);
        // so that will cause LazyInitializationException for below code if we have enabled the LAZY fetch type,
        // as it will only fetch student details not passport
        // so below both statements will cause issue as student tostring method will also need info about
        // passport so it can print it.
        logger.info("Student -> {}", student);
//        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void retrievePassportAndAssociatedStudent(){
        Passport passport = em.find(Passport.class, 40001);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent());
    }

}