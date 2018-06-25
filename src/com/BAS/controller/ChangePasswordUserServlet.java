package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.ChangePasswordUserDAO;
import com.BAS.model.CredentialsForUser;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordUserServlet")
public class ChangePasswordUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpSession sess= request.getSession();
		   CredentialsForUser userCredentials = new CredentialsForUser();
		   
		   
	       String userName = request.getParameter("userName");
	       String oldPassword= request.getParameter("currentPassword");
			String newPassword= request.getParameter("newPassword");
		    String confirmPassword = request.getParameter("confirmPassword"); 
		    
		 
		    userCredentials.setUserName(userName);
		    userCredentials.setOldPassword(oldPassword);
		    userCredentials.setNewPassword(newPassword);
		    userCredentials.setConfirmPassword(confirmPassword);
			System.out.println(userCredentials.getOldPassword() + " " +userCredentials.getNewPassword() + " " + userCredentials.getConfirmPassword());
			ChangePasswordUserDAO changePasswordUserDAO = new ChangePasswordUserDAO();
			boolean check = changePasswordUserDAO.updatePassword(userCredentials);
			if(check) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/UserLogin.jsp");
				sess.setAttribute("USERNAME", userCredentials.getUserName());
				sess.setAttribute("OLDPASSWORD", userCredentials.getOldPassword());
				dispatcher.forward(request, response); 
			}
			else {
				RequestDispatcher dispathcerOne = request.getRequestDispatcher("/ChangePasswordUserFailed.jsp");
				dispathcerOne.forward(request, response);
			}
		
	}

}
