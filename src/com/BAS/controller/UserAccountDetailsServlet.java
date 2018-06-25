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

import com.BAS.dao.RegisterAccountDAO;

/**
 * Servlet implementation class UserAccountDetailsServlet
 */
@WebServlet("/UserAccountDetailsServlet")
public class UserAccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "";
		HttpSession sess=request.getSession();
		String name=(String) sess.getAttribute("USERNAME");
		System.out.println("UserAccountDetailsServlet | doget() | entered with name " + name);
		String action = request.getParameter("action");
		RegisterAccountDAO registerAccountDAO = new RegisterAccountDAO();
		if(action.equalsIgnoreCase("listUserAccountDetails")) {
			forward = "/UserAccountDetails.jsp";
			try {
				request.setAttribute("UserAccountDetails", registerAccountDAO.getAccountDetails(name));
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
