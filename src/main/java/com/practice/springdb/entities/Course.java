package com.practice.springdb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

public class Course {
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


