package com.BAS.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BAS.dao.AccountDetailsDAO;
import com.BAS.dao.AccountStatementDAO;
import com.BAS.model.AccountStatement;

/**
 * Servlet implementation class AccountDetailsServlet
 */
@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String forward = "";
         String action = request.getParameter("action");
         AccountDetailsDAO accountDetailsDAO = new AccountDetailsDAO();
         
        if(action.equalsIgnoreCase("listAccountDetails")) {
        	forward = "/AccountDetails.jsp";
        	
				try {
					request.setAttribute("Accounts", accountDetailsDAO.getDetailsList());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
        	
        }
        else if(action.equalsIgnoreCase("statement")) {
        	 forward = "/AccountStatement.jsp";
        	 String name = request.getParameter("userName");
        	 AccountStatementDAO accountStatementDAO = new AccountStatementDAO();
			try {
				request.setAttribute("Statements", accountStatementDAO.getAccountStatement(name));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	 }
        
         RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
         dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
