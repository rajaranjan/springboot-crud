package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    // define field for entity manager
    private EntityManager entityManager;

    // setup constructor injection
    public EmployeeDaoJpaImpl (EntityManager theEntitymanager) {
        entityManager = theEntitymanager;
    }
    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // save the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // return the saved employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // remove the employee
        entityManager.remove(theEmployee);

    }
}
