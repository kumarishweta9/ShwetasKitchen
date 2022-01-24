package com.kitchen.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/FoodControllerServlet")
public class FoodControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodDbUtil foodDbUtil;
	//private HttpServletRequest request;
	//private HttpServletResponse response;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException{
		super.init();
		
		try {
			foodDbUtil = new FoodDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
		switch (theCommand) {
		
		case "LIST":
			listCustomer(request, response);
			break;
			
		case"ADD":
			addCustomer(request, response);
			break;
			
		case"Load":
			loadCustomer(request, response);
			break;
			
		case"UPDATE":
			updateCustomer(request, response);
			break;
		
		case "DELETE":
			deleteCustomer(request, response);
			break;
			
		default:
			listCustomer(request, response);	
			
		}
	}
	catch (Exception exc) {
		throw new ServletException(exc);
	}
	
}
		private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student id from form data
		String theCustomerId = request.getParameter("customerId");
		
		// delete student from database
		foodDbUtil.deleteCustomer(theCustomerId);
		
		// send them back to "list students" page
		listCustomer(request, response);
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		//int dateOfBirth = Integer.parseInt(request.getParameter("dateOfBirth"));
		String state = request.getParameter("state");
		String postalCode = request.getParameter("postalCode");
		String comment = request.getParameter("comment");
		
		// create a new student object
		Customer theCustomer = new Customer(id, firstName, lastName, address, state,postalCode, comment);
		
		// perform update on database
		foodDbUtil.updateCustomer(theCustomer);
		
		// send them back to the "list students" page
		listCustomer(request, response);
		
	}

	private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student id from form data
		String theCustomerId = request.getParameter("customerId");
		
		// get student from database (db util)
		Customer theCustomer = foodDbUtil.getCustomer(theCustomerId);
		
		// place student in the request attribute
		request.setAttribute("THE_CUSTOMER", theCustomer);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-customer-form.jsp");
		dispatcher.forward(request, response);		
	}



private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

	// read student info from form data
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String address = request.getParameter("address");
	String state = request.getParameter("state");
	String postalCode = request.getParameter("postalCode");
	String comment = request.getParameter("comment");
	
	// create a new student object
	Customer theCustomer = new Customer(firstName, lastName, address, state,postalCode, comment);
	
	// add the student to the database
	foodDbUtil.addCustomer(theCustomer);
			
	// send back to main page (the student list)
	listCustomer(request, response);
}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {

	// get students from db util
	List<Customer> customer = foodDbUtil.getCustomer();
	
	// add students to the request
	request.setAttribute("CUSTOMER_LIST", customer);
			
	// send to JSP page (view)
	RequestDispatcher dispatcher = request.getRequestDispatcher("/list-customer.jsp");
	dispatcher.forward(request, response);
	}

}


