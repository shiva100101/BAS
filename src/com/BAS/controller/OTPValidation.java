package com.BAS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BAS.dao.AccountDetailsDAO;
import com.BAS.dao.FundTransferDAO;
import com.BAS.model.FundTransfer;

/**
 * Servlet implementation class OTPValidation
 */
@WebServlet("/OTPValidation")
public class OTPValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OTPValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		FundTransferDAO fundTransferDAO = new FundTransferDAO();
		String otp = request.getParameter("oneTimePassword");
		FundTransfer fundTransfer  = new FundTransfer();
		HttpSession sess = request.getSession();
		fundTransfer=  (FundTransfer) sess.getAttribute("OTP");
		System.out.println(otp);
		String number = request.getParameter("number");
		if(otp.equalsIgnoreCase(number)) {
			fundTransfer.setStatus("SUCCESS");
			boolean check = fundTransferDAO.updatDetails(fundTransfer);
			if(check) {
				
				AccountDetailsDAO accountDetailsDAO = new AccountDetailsDAO();
				accountDetailsDAO.updateBalance(fundTransfer);
				forward = "/FundTransferSuccessful.jsp";
			}
			
		}
		else {
			fundTransfer.setStatus("FAILED");
			boolean check = fundTransferDAO.updatDetails(fundTransfer);
			if(check) {
				forward = "/FundTransfer.jsp";
			}
			
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HI");
		System.out.println(request.getParameter("pin"));
		String forward = "/Validate.jsp";
		request.setAttribute("oneTimePassword", request.getParameter("pin"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		
	}

}
