package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.ChangePasswordDAO;
import com.BAS.model.UserCredentials;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession sess= request.getSession();
	       
	       String userName = request.getParameter("userName");
			String newPassword= request.getParameter("newPassword");
		    String confirmPassword = request.getParameter("confirmPassword"); 
		    
		    UserCredentials userCredentials = new UserCredentials();
		    userCredentials.setUserName(userName);
		    userCredentials.setNewPassword(newPassword);
		    userCredentials.setConfirmPassword(confirmPassword);
			System.out.println(userCredentials.getNewPassword() + " " + userCredentials.getConfirmPassword());
			ChangePasswordDAO changePasswordDAO = new ChangePasswordDAO();
			boolean check = changePasswordDAO.updatePassword(userCredentials);
			if(check) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/UserLogin.jsp");
				sess.setAttribute("USERNAME", userCredentials.getUserName());
				dispatcher.forward(request, response); 
			}
			else {
				RequestDispatcher dispathcerOne = request.getRequestDispatcher("/UserPasswordFailed.jsp");
				dispathcerOne.forward(request, response);
			}
			

	}

}
