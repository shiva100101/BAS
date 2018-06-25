package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.UserLoginDAO;
import com.BAS.model.UserLogin;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess= request.getSession();
	       
	       
		String name= request.getParameter("userName");
	    String password = request.getParameter("password"); 
	    
	    UserLogin a = new UserLogin();
	    a.setUserName(name);
		a.setPassword(password);
		UserLoginDAO userLoginDao = new UserLoginDAO();
		boolean check = userLoginDao.checkCredentials(a);
		if(check) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMainPage.jsp");
			sess.setAttribute("USERNAME", a.getUserName());
			dispatcher.forward(request, response); 
		}
		else {
			RequestDispatcher dispathcerOne = request.getRequestDispatcher("/UserLoginFailed.jsp");
			dispathcerOne.forward(request, response);
		}
		
	
	}}

