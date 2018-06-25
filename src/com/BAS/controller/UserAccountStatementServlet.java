package com.BAS.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.AccountDetailsDAO;
import com.BAS.dao.AccountStatementDAO;

/**
 * Servlet implementation class UserAccountStatementServlet
 */
@WebServlet("/UserAccountStatementServlet")
public class UserAccountStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountStatementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @param accountDetailsDAO 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountDetailsDAO accountDetailsDAO  = new AccountDetailsDAO();
		AccountStatementDAO accountStatementDAO = new AccountStatementDAO();
		HttpSession sess=request.getSession();
		String name=(String) sess.getAttribute("USERNAME");
		System.out.println("UserAccountStatementServlet | doget() | entereed with name " + name);
		String forward ="";
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("UserListStatementDetails")) {
			forward = "/UserAccountStatement.jsp";
			try {
				request.setAttribute("UserAccountStatement", accountStatementDAO.getAccountStatement(name));
				request.setAttribute("Balance", accountDetailsDAO.fetchbalance(name));
			} catch (SQLException e) {
				// TODO Auto-generated catch bloc
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
