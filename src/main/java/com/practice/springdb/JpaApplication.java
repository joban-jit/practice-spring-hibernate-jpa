package com.practice.springdb;

import com.practice.springdb.entity.Person;
import com.practice.springdb.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class JpaApplication
        implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository personJpaRepository;


    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = personJpaRepository.findAll();
        logger.info("All users - > {} ",personList);

        Person personById = personJpaRepository.findById(10001);
        logger.info("Person with id: 10001: {} ", personById);
        personJpaRepository.deleteById(10002);
        Person personInserted = personJpaRepository.insert_or_update(new Person("Ron", "London", LocalDate.now()));
        logger.info("Inserted new Person: No. of rows inserted {}", personInserted);
        Person personUpdated = personJpaRepository.insert_or_update(new Person(10001, "Ginny", "London", LocalDate.now()));
        logger.info("Updated 10001: No. of rows updated {}", personUpdated);
    }
}
