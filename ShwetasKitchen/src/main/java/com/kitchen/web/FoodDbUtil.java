package com.kitchen.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class FoodDbUtil {
	private DataSource dataSource;
	//private List list;
	
	public FoodDbUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}
	public List<Customer> getCustomer() throws Exception {
		
		List<Customer> customer = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from customer order by last_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String address = myRs.getString("address");
				String state = myRs.getString("state");
				String postalCode = myRs.getString("postal_code");
				String comment = myRs.getString("comment");
				
				// create new student object
				Customer tempCustomer = new Customer(id, firstName, lastName, address,state,postalCode,comment);
				
				// add it to the list of students
				customer.add(tempCustomer);				
			}
			
			return customer;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into customer "
					   + "(first_name, last_name, address, state, postal_code, comment) "
					   + "values (?, ?, ?, ?,?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setString(4, theCustomer.getState());
			myStmt.setString(5, theCustomer.getPostalCode());
			myStmt.setString(6, theCustomer.getComment());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	public Customer getCustomer(String theCustomerId) throws Exception {

		Customer theCustomer = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int customerId;
		
		try {
			// convert student id to int
			customerId = Integer.parseInt(theCustomerId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from customer where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String address = myRs.getString("address");
				String state = myRs.getString("state");
				String postalCode = myRs.getString("postal_code");
				String comment = myRs.getString("comment");
				
				
				// use the studentId during construction
				theCustomer = new Customer(customerId, firstName, lastName, address, state,postalCode,comment);
			}
			else {
				throw new Exception("Could not find student id: " + customerId);
			}				
			
			return theCustomer;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCustomer(Customer theCustomer) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update customer "
						+ "set first_name=?, last_name=?, address=?, state=?, postal_code=?,comment=?"
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setInt(4, theCustomer.getId());
			myStmt.setString(5, theCustomer.getState());
			myStmt.setString(6, theCustomer.getPostalCode());
			myStmt.setString(7, theCustomer.getComment());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteCustomer(String theCustomerId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int customerId = Integer.parseInt(theCustomerId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from customer where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}



