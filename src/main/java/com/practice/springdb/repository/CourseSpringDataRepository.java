package com.practice.springdb.repository;

import com.practice.springdb.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path = "courses")
// by adding above annotation we are exposing this repository as rest resource
public interface CourseSpringDataRepository extends JpaRepository<Course, Integer> {
    // we get a lot of functionality for free
    // JpaRepository extends PagingAndSortingRepository which extends CrudRepository
    // CrudRepository handle all the basic operations: findById, findAll, save, saveAll, count, delete,
    // deleteById, deleteAll and some others

    // JpaRepository handles stuff related to JPA
    // e.g flush, saveAndFlush,

    // we can define our custom queries, by following specfic pattern or standard
    List<Course> findByName(String name); // you can also use queryByName, both are acceptable
    int countByName(String name);
    Course findByNameAndId(String name, int id);
    List<Course> findByNameOrderByIdDesc(String name);
    void deleteByName(String name);

    // using JPQL
    @Query("select c from Course c where name like 'dummy%'") // JPQL query
    List<Course> coursesWithDummyName();

    // using native query
    @Query(value="select * from Course where name like 'dummy%'", nativeQuery = true) // using native query
    List<Course> coursesWithDummyNameNativeQuery();

    // using a named query, which we already defined in Course class
    @Query(name = "query_get_courses_with_like_keyword")
    List<Course> coursesWithDummyNameNativeQueryWithName();
}
