package com.simplilearn.JDBCDemos;

import java.util.List;

import com.simplilearn.JDBCDemos.model.Customer;
import com.simplilearn.jdbcDemos.db.DB;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DB db= new DB();
        db.createConnection();
        
       /* Customer customer =new Customer();
        customer.setCid(3);
        customer.setName("Ajay Krishna");
        customer.setPhno("7333564497");
        customer.setEmail("ak@example.com");
        customer.setBirthDate("2000-03-09");
        customer.setAge(27);
        customer.setTemperature(99.4F);*/
        
       // Customer customer =new Customer();
        //customer.setCid(2);
        
       // System.out.println(customer);
        
       // db.createCustomer(customer);
        
       // db.updateCustomer(customer);
        
       // db.deleteCustomer(customer);
        
        List<Customer> customers =db.getAllCustomers();
        for(Customer customer: customers) {
        	System.out.println(customer);
        }
        
        db.closeConnection();
    }
}
