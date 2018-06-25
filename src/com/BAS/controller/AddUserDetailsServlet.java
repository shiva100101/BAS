package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BAS.dao.UserDetailDAO;
import com.BAS.model.UserDetails;

/**
 * Servlet implementation class AddUserDetailsServlet
 */
@WebServlet("/AddUserDetailsServlet")
public class AddUserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String forward = "";
		String userName = request.getParameter("userName");
   	    long accNumber = Long.parseLong(request.getParameter("accNumber"));
   	    String emailId = request.getParameter("emailId");
   	    long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
   	    String registerDate = request.getParameter("registerDate");
   	    System.out.println("The values are " + userName +" "+ accNumber +" "+ emailId +" "+ mobileNumber + " "+registerDate);
   	 UserDetails userDetails = new UserDetails();
   	 userDetails.setRegisterDate(registerDate);
	   	userDetails.setUserName(userName);
	   	userDetails.setAccountNumber(accNumber);
	   	userDetails.setEmailId(emailId);
	   	userDetails.setMobileNumber(mobileNumber);
   	    System.out.println("The values are " + userDetails.getUserName() +" "+ userDetails.getAccountNumber() +" "+ userDetails.getEmailId() +" "+ userDetails.getMobileNumber() + " "+userDetails.getRegisterDate());

	   	UserDetailDAO userDetailDAO = new UserDetailDAO();
		
	    boolean check = userDetailDAO.addUser(userDetails);
	    if(check) {
	   	 forward = "/UserIndex.jsp";
	      }
	      else {
	   	   forward = "/Failed.jsp";
	      }
	
   	   
   	    
   	   
   	
   	
   
	
	RequestDispatcher view = request.getRequestDispatcher(forward);
	view.forward(request, response);
	}
	
	

}
