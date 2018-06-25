package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.AdminLoginDAO;
import com.BAS.dao.ChangePasswordDAO;
import com.BAS.dao.NewpinDAO;
import com.BAS.model.AdminLogin;
import com.BAS.model.PinCredentials;
import com.BAS.model.UserCredentials;

/**
 * Servlet implementation class Newpin
 */
@WebServlet("/Newpin")
public class Newpin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newpin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession sess= request.getSession();
	       
	       String userName = request.getParameter("userName");
	       String oldpin = request.getParameter("oldpin");
			String Pin= request.getParameter("pin");
		    String confirmpin = request.getParameter("confirmPin"); 
		    
		    PinCredentials  pinCredentials = new PinCredentials();
		    pinCredentials.setUserName(userName);
		    pinCredentials.setOldpin(oldpin);
		    pinCredentials.setPin(Pin);
		    pinCredentials.setConfirmPin(confirmpin);
			System.out.println(pinCredentials.getPin()+ " " + pinCredentials.getConfirmPin());
			 NewpinDAO  NewpinDAO  = new  NewpinDAO();
			boolean check = NewpinDAO.updatePin(pinCredentials);
			if(check) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/NewPin.jsp");
				sess.setAttribute("USERNAME", pinCredentials.getUserName());
				sess.setAttribute("OLDPIN", pinCredentials.getOldpin());
				dispatcher.forward(request, response); 
			}
			else {
				RequestDispatcher dispathcerOne = request.getRequestDispatcher("/Pinfailed.jsp");
				dispathcerOne.forward(request, response);
			}
			

	}

}
