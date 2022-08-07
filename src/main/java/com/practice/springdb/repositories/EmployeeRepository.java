package com.practice.springdb.repositories;

import com.practice.springdb.entities.Employee;
import com.practice.springdb.entities.FullTimeEmployee;
import com.practice.springdb.entities.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Transactional
    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<Employee> retrieveAllEmployees(){
        TypedQuery<Employee> typedQuery = em.createQuery("select e from Employee e", Employee.class);
        return typedQuery.getResultList();
    }

    // below methods are used when we enabled @MappedSuperclass annotation on Employee class
    public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
        TypedQuery<FullTimeEmployee> typedQuery = em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class);
        return typedQuery.getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        TypedQuery<PartTimeEmployee> typedQuery = em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class);
        return typedQuery.getResultList();
    }


}
