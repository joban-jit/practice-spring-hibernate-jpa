package com.practice.springdb;

import com.practice.springdb.entity.Person;
import com.practice.springdb.jdbc.PersonJdbcDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    }
}
