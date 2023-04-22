package com.simplilearn.jdbcDemos.db;

import java.util.List;

import com.simplilearn.JDBCDemos.model.Customer;

public interface DAO {
       void createConnection();
       void closeConnection();
       void createCustomer(Customer customer);
       void updateCustomer(Customer customer);
       void deleteCustomer(Customer customer);
       List<Customer> getAllCustomers();
}
