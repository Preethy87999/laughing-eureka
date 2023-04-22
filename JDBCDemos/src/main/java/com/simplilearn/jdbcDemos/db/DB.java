package com.simplilearn.jdbcDemos.db;

import java.sql.Connection;
import java.sql.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import com.simplilearn.JDBCDemos.model.Customer;

public class DB implements DAO{
	
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("1. Driver load...");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void createConnection() {
		
		try {
			
            String user="preethi";
		    String password="Preethi#3";
		    String database="mydb";
			
			String url="jdbc:mysql://localhost/"+database;
			
			connection = DriverManager.getConnection(url,user,password);
			
			System.out.println("2.  Connection Created...");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void closeConnection() {
		
	   try {
		   if(connection !=null) {
		   connection.close();
		   System.out.println("3. Connection  close...");
		   }else {
			   System.err.println("3. Connection does not exist...");
		   }
	   }
	   catch (Exception e){
		   e.printStackTrace();
	   }
		
	}

	public void createCustomer(Customer customer) {
		
		try {
			
			//SimpleDateFormat pattern1 =new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat pattern2 =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			//String date1 = pattern1.format(new Date(0, 0, 0));
			String date2 = pattern2.format(new Date(0));
			
			//System.out.println("date1: "+date1);
			System.out.println("date2: "+date2);
			
			customer.setInDateTime(date2);
			customer.setOutDateTime(date2);
			
			/*String sql = "insert into Customer values(null,'"+customer.getName()+"','"+customer.getPhno()+"','"
			+customer.getEmail()+"','"+customer.getBirthDate()+"',"+customer.getAge()+",'"+customer.getInDateTime()+"','"
					+customer.getOutDateTime()+"',"+customer.getTemperature()+")";
			
			System.out.println("SQL: "+sql);
			
			statement = connection.createStatement();
			int result = statement.executeUpdate(sql);
			
			String message =result > 0 ? "Customer  "+   customer.getName()  +  " Created Successfully...  " : "  customer  " +  customer.getName()  + "  Not Created Successsfully...";
			
			System.out.println(message);*/
			
			String sql="insert into  Customer values(null,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPhno());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getBirthDate());
			preparedStatement.setInt(5, customer.getAge());
			preparedStatement.setString(6, customer.getInDateTime());
			preparedStatement.setString(7, customer.getOutDateTime());
			preparedStatement.setFloat(8, customer.getTemperature());
			
            int result = preparedStatement.executeUpdate();
			
			String message =result > 0 ? "Customer  "+   customer.getName()  +  " Created Successfully...  " : "  customer  " +  customer.getName()  + "  Not Created Successsfully...";
			
			System.out.println(message);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}

	public void updateCustomer(Customer customer) {
		
		try {
			
			String sql="update Customer set name=?,phno=?, email=? where cid =?";
            preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPhno());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setInt(4, customer.getCid());
			
            int result = preparedStatement.executeUpdate();
			
			String message =result > 0 ? "Customer  "+   customer.getName()  +  " Update Successfully...  " : "  customer  " +  customer.getName()  + "  Not Updated Successsfully...";
			
			System.out.println(message);
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		
		
	}

	public void deleteCustomer(Customer customer) {
try {
			
			String sql="delete from Customer  where cid =?";
            preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, customer.getCid());
			
            int result = preparedStatement.executeUpdate();
			
			String message =result > 0 ? "Customer  "+   customer.getCid()  +  " Delete Successfully...  " : "  customer  " +  customer.getCid()  + "  Not Deleted Successsfully...";
			
			System.out.println(message);
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers =  new ArrayList<Customer>();
try {
			
			String sql="select * from Customer";
            preparedStatement = connection.prepareStatement(sql);
			
            ResultSet set = preparedStatement.executeQuery();
            
            while(set.next()) {
            	Customer customer = new Customer();
            	customer.setCid(set.getInt(1));
            	customer.setName(set.getString(2));
            	customer.setPhno(set.getString(3));
            	customer.setEmail(set.getString(4));
            	customer.setBirthDate(set.getString(5));
            	customer.setAge(set.getInt(6));
            	customer.setInDateTime(set.getString(7));
            	customer.setOutDateTime(set.getString(8));
            	customer.setTemperature(set.getFloat(9));
            	customers.add(customer);
            	
            }
			
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		
		return customers;
	}
       
}
