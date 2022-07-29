package com.practice.springdb;

import com.practice.springdb.entity.Person;
import com.practice.springdb.jdbc.PersonJdbcDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class PracticeSpringDbApplication
        implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDAO dao;


    public static void main(String[] args) {
        SpringApplication.run(PracticeSpringDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = dao.findAll();
        logger.info("All users - > {} ",personList);
        Person personById = dao.findById(10001);
        logger.info("Person with id: 10001: {} ", personById);
        int numberOfRowsDeleted = dao.deleteById(10002);
        logger.info("Deleted 10003, No of rows deleted : {}", numberOfRowsDeleted);
        int numberOfRowsInserted = dao.insert(new Person(10004, "Ron", "London", LocalDate.now()));
        logger.info("Inserted new Person: No. of rows inserted {}", numberOfRowsInserted);
        int numberOfRowsUpdated = dao.update(new Person(10001, "Ginny", "London", LocalDate.now()));
        logger.info("Updated 10003: No. of rows updated {}", numberOfRowsInserted);
    }
}
