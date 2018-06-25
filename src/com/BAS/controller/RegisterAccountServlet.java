package com.BAS.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BAS.dao.RegisterAccountDAO;
import com.BAS.model.RegisterAccount;

/**
 * Servlet implementation class RegisterAccountServlet
 */
@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
        

		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean check = false;
	        String name = request.getParameter("fName");
		    String lastName = request.getParameter("lName"); 
			String password = request.getParameter("password");
		    String confirmPassword = request.getParameter("cPassword"); 
			String email = request.getParameter("email");
		    long mobile =  Long.parseLong(request.getParameter("mNumber")) ; 
			String date = request.getParameter("date");
		    String gender = request.getParameter("gender"); 
		    String address = request.getParameter("address");
		    String city = request.getParameter("city"); 
			String state = request.getParameter("state");
		    long zipCode =  Long.parseLong(request.getParameter("zip")) ;  
		    String accountType = request.getParameter("account");
		    long pin =  Long.parseLong(request.getParameter("pin")) ; 
			long vPin =  Long.parseLong(request.getParameter("vPin")) ;
			long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
		
	 //pojo
		//set vales to pojo
		
		RegisterAccount registerAccount = new RegisterAccount();
		registerAccount.setName(name);
		registerAccount.setLastName(lastName);       
		registerAccount.setPassword(password);
		registerAccount.setConfirmPassword(confirmPassword);
		registerAccount.setEmail(email);
		registerAccount.setMobile(mobile);
		registerAccount.setDate(date);
		registerAccount.setGender(gender);
		registerAccount.setAddress(address);
		registerAccount.setCity(city);
		registerAccount.setState(state);
		registerAccount.setZipCode(zipCode);
		registerAccount.setAccountType(accountType);
		registerAccount.setPin(pin);
		registerAccount.setvPin(vPin);
		registerAccount.setAccountNumber(accountNumber);
		registerAccount.setBalance(1000);
		if (registerAccount.getPin() == registerAccount.getvPin())
				 {
			RegisterAccountDAO registerAccountDAO = new RegisterAccountDAO();
			check = registerAccountDAO.addUser(registerAccount);
		}
		
		
		System.out.println("the checked value is "+check);
		if(check) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserLogin.jsp");
			 dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RegisterFailed.jsp");
			 dispatcher.forward(request, response);
		}

		
    }
 
}