package com.practice.springdb.jpa;

import com.practice.springdb.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    // connect to the database
    // it manages the entity.
    // all operation goes through entity manager
    // all the operation you performing in specific session, all stored inside persistance context
    // EntityManager is the interface for PersistanceContext
    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll(){
        // using JPQL- java persistance Query language
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_person", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(int id){
        return entityManager.find(Person.class, id);
    }

    public Person insert_or_update(Person person){
        return entityManager.merge(person);
    }

    public void deleteById(int id){
        Person person = findById(id);
        entityManager.remove(person);
    }

}
