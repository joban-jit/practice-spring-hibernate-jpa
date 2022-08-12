package com.practice.springdb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter()
@ToString(exclude = {"id", "reviews", "students", "lastUpdatedDate", "createdDate"})
@NoArgsConstructor
@Entity
// use this @Table if entity name is different from table name in db
@Table(name="CourseDetails") // it will map to table name = "course_details" as by default it convert the name "CourseDetails"
// to "course_details" as per some db standards
// so our entity name = Course
// and our table name = CourseDetails which converts to "course_details" while create a table as per some standards

@NamedQuery(name="query_get_all_courses", query="select c from Course c") // we have created a named query
// and we can use it other places
@NamedQuery(name="query_get_courses_with_like_keyword", query="select c from Course c where name like 'Work%'")
//if you have multiple queries you can also use below annotation
// but don't use both of them together.
//@NamedQueries(value={
//        @NamedQuery(name="query_get_all_courses", query="select c from Course c"),
//        @NamedQuery(name="query_get_courses_with_like_keyword", query="select c from Course c where name like 'Work%'")
//})
@Cacheable// to cache this entity as part of second level cache (L2C- level two cache)
@SQLDelete(sql = "update course_details set is_deleted=true where id = ?") //hibernate annotation not jpa annotation
// this above sql query will run when we try to delete records in Course entity
@Where(clause = "is_deleted = false") // we have added this as with normal select query it still retrieves the
// record which has been soft deleted , so we need to update the where clause

//NOTE: hibernate doesn't know what is happening with where clause or sqldelete like is what is happening with
// is_deleted column like if it being set to false, hibernate doesn't know about that, as that is being done in query
// we can fix that using @PreRemove( JPA Entity life cycle)

// we have different pre/post annotations which are part of JPA Entity Life Cycle which we can use to do something
// before or after we do basic sql operations like load, persist, delete etc.

// NOTE: above implementation of soft delete: SQLDelete and Where will not work on native query, so you need to handle that
// on your own
public class Course {
    private static Logger logger = LoggerFactory.getLogger(Course.class);
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "fullname", nullable=false)
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "course") // course is the variable in Review class
    // for OneToMany type of relationship by default the FetchType is LAZY
    // With Fetch Type EAGER: it queries the table with JOINs
    private List<Review> reviews = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "courses") // courses is the name of variable in Student class, thus makes Student owning side of
    // relationship
    @JsonIgnore // to ignore this in response when we have enabled the @RepositoryRestResource annotation
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted; // adding this for soft deletes
    // by default it values will be false, when try the soft delete it's value becomes true

    @PreRemove
    // now to tell hibernate that it should set isDeleted value to true whenever we delete a record
    // we can use this @PreRemove annotation to do that just before we do soft delete
    private void preRemove(){
        logger.info("Setting isDeleted to true");
        this.isDeleted = true;
    }

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

}


