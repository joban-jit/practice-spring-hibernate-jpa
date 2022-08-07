package com.practice.springdb.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "EmployeeType") // if you use Inheritance Type  = Single_table and JOINED then you can use
// this @DiscriminatorColumn annotation to give your own name for the discriminator column which is created by default
// to differentiate between which type of employee it is. By default that column name is : D_TYPE

// there is another option where we won't use the above inheritance annotation rather we will use
// @MappedSuperclass, it means this class is only here for mapping so It can't be entity thus no table for this, so you need to remove
// entity from it and change the respective logic. An entity cannot be annotated with both @Entity and @MappedSuperclass
//@MappedSuperclass

// in MappedSuperclass and with inheritance TABLE_PER_CLASS we would have duplicated columns
@NoArgsConstructor
@ToString(exclude = "id")
public abstract class Employee {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable=false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
