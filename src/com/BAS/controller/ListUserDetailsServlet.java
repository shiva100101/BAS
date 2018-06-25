package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BAS.dao.UserDetailDAO;
import com.BAS.model.RegisterAccount;
import com.BAS.model.UserDetails;

/**
 * Servlet implementation class listUserDetails
 */
@WebServlet("/ListUserDetailsServlet")
public class ListUserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "";
		String action = request.getParameter("action");

		// listUserDetails?action=listUserDetails
		if (action.equalsIgnoreCase("listUserDetails")) {
			UserDetailDAO userDetailDAO = new UserDetailDAO();
			forward = "/UserDetails.jsp";
			request.setAttribute("Users", userDetailDAO.getAllUsers());
		} else if (action.equalsIgnoreCase("update")) {
			forward = "/AddUserDetails.jsp";
			String userName = request.getParameter("userName");
			UserDetailDAO userDetailDAO = new UserDetailDAO();
			
			request.setAttribute("user", userDetailDAO.getUserByName(userName));
			request.setAttribute("action", action);
		} else if (action.equalsIgnoreCase("delete")) {
			String userName = request.getParameter("userName");
			System.out
					.println("ListUserDetailsServlet | doGet() | entereed inside delete with name "
							+userName);
			UserDetailDAO userDetailDAO = new UserDetailDAO();
			userDetailDAO.delete(userName);
			userDetailDAO.deleteRegister(userName);
			userDetailDAO.deleteAccount(userName);
			forward = "/UserDetails.jsp";
			request.setAttribute("Users", userDetailDAO.getAllUsers());
			request.setAttribute("action", action);
		} else {
			System.out.println("Entered into add/update");
			forward = "/AddUserDetails.jsp";
			request.setAttribute("action", action);
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean check = false;
		String forward = "";
		String action = request.getParameter("action");
		String userName = request.getParameter("userName");
		long accNumber = Long.parseLong(request.getParameter("accNumber"));
		String emailId = request.getParameter("emailId");
		long mobileNumber = Long
				.parseLong(request.getParameter("mobileNumber"));
		String registerDate = request.getParameter("registerDate");
		System.out.println("The values are " + userName + " " + accNumber + " "
				+ emailId + " " + mobileNumber + " " + registerDate);
		UserDetails userDetails = new UserDetails();
		userDetails.setRegisterDate(registerDate);
		userDetails.setUserName(userName);
		userDetails.setAccountNumber(accNumber);
		userDetails.setEmailId(emailId);
		userDetails.setMobileNumber(mobileNumber);
		System.out.println("The values are " + userDetails.getUserName() + " "
				+ userDetails.getAccountNumber() + " "
				+ userDetails.getEmailId() + " "
				+ userDetails.getMobileNumber() + " "
				+ userDetails.getRegisterDate());

		UserDetailDAO userDetailDAO = new UserDetailDAO();
		System.out.println(action);

		if (action.equalsIgnoreCase("add")) {
			check = userDetailDAO.addUser(userDetails);
		} else {
			userDetails.setUserName(userName);
			check = userDetailDAO.updateUser(userDetails);
			check = userDetailDAO.updateRegister(userDetails);
			check = userDetailDAO.updateaccount(userDetails);
			
		}
		System.out.println(check);
		if (check) {
			forward = "/UserDetails.jsp";
			request.setAttribute("Users", userDetailDAO.getAllUsers());
		} else {
			forward = "/Failed.jsp";
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
