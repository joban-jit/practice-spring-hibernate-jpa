package com.practice.springdb.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@ToString(exclude = {"id", "courses", "address"})
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch=FetchType.LAZY) // by default, it is FetchType.EAGER
    private Passport passport;

    @Embedded
    private Address address;


    @Setter(AccessLevel.NONE)
    @ManyToMany // by default ManyToMany use FetchType = LAZY, also not recommended to use EAGER on ManyToMany
    @JoinTable(
            name = "STUDENT_COURSE", // custom name of join table
            joinColumns = @JoinColumn(name = "STUDENT_ID"), // custom column name of join column of this owning side Table i.e. Student
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")// custom column name of join column of other table i.e. CourseDetails
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String name){
        this.name = name;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
}
