package com.luv2code.cruddemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Override
    public void addAccount() {

        System.out.println(getClass() + ": Doing My DB Work to add in account");
    }
}
