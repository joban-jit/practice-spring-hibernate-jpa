package com.practice.springdb;

import com.practice.springdb.entities.Course;
import com.practice.springdb.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course10003 = courseRepository.findById(10003);
		logger.info("Course 10003: {}", course10003);
//		courseRepository.deleteById(10001);

		logger.info("New Course has been added: {} ", courseRepository.save(new Course("Git in 60 mins")));
		course10003.setName("AWS Data Analyst Specialist");
		logger.info("Updated the existing course {} ", courseRepository.save(course10003));
	}
}