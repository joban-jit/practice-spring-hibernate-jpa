package com.practice.springdb.repository;

import com.practice.springdb.entities.Passport;
import com.practice.springdb.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class StudentRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Student findById(int id){
        return em.find(Student.class, id);
    }

    @Transactional
    public Student save(Student student){
        if(student.getId() == 0){
            em.persist(student);
        }else{
            student = em.merge(student);
        }
        return student;
    }

    @Transactional
    public void deleteById(int id){
        Student student = findById(id);
        Optional.ofNullable(student).ifPresentOrElse(s->{
            em.remove(s);
            logger.info("Student with {} id has been removed", id);
        },
                ()->logger.info("Given course doesn't exsit")
        );
    }

    @Transactional
    public void saveStudentWithPassport(){
        Passport passport = new Passport("Z123476");
        em.persist(passport);
        // if you want your changes to be sent the database earlier, then you can use
        // em.flush() so before this line all the changes would send to the db
        // if let's say below student.setPassport(passport) failed, it will roll back the earlier changes in db
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
        // at the end of transaction only it will send data to database, so till this point there is no
        // new passport or student created it will wait till it complete the execution of all code in this
        // method
    }

    @Transactional// when you create a transactional, you also create Persistence Context and killed after transaction
    // completes
    // persistance context is a place all the entities you are operating are stored i.e. student, passport
    // persistance context = hibernate session, it tracks the changes
    // in JPA we use EntityManager to talk to Persistence context,
    public void understandingPersistenceContext(){
            // DB operation 1 - Retrieve student
            Student student = em.find(Student.class, 20001);
            // PersistanceContext contains (student)

            // DB operation 2 - Retrieve passport
            Passport passport = student.getPassport();
            // PersistanceContext contains (student, passport)

            // DB operation 3 - update passport
            passport.setNumber("E123457");
            // PersistanceContext contains (student, passport_updated)

            // DB operation 4 - update student
            student.setName("John - Updated");
            // PersistanceContext contains (student_updated, passport)

            // so after the end of transactional is completed all the changes are sent to db

        }

}
