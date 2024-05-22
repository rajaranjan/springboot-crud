package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.dao.EmployeeDao;
import com.luv2code.cruddemo.dao.EmployeeRepository;
import com.luv2code.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if(result.isPresent()) {
            theEmployee = result.get();
        } else {
            // could not find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

//    private EmployeeDao employeeDao;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeDao theEmployeeDao) {
//        employeeDao = theEmployeeDao;
//    }
//    @Override
//    public List<Employee> findAll() {
//        return employeeDao.findAll();
//    }
//
//    @Override
//    public Employee findById(int theId) {
//        return employeeDao.findById(theId);
//    }
//
//    @Override
//    @Transactional
//    public Employee save(Employee theEmployee) {
//        return employeeDao.save(theEmployee);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(int theId) {
//        employeeDao.deleteById(theId);
//    }
}
