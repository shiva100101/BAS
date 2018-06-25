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
import com.BAS.dao.FundTransferDAO;
import com.BAS.dao.RegisterAccountDAO;
import com.BAS.model.FundTransfer;
import com.BAS.util.GenerateOTP;

/**
 * Servlet implementation class FundTransferServlet
 */
@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//check if two bank account is same or not
		//if same first do debit and then credit
		//another condition for debit is check balance
		
		
		HttpSession sess= request.getSession();
		String forward = "";
		GenerateOTP generateOTP = new GenerateOTP();
		FundTransferDAO fundTranserDAO = new FundTransferDAO();
	String receiverBankName = request.getParameter("receiverBankName");
	String receiverName = request.getParameter("receiverName");
	long receiverAccountNumber = Long.parseLong(request.getParameter("receiverAccountNumber"));
	long senderAccountNumber = Long.parseLong(request.getParameter("senderAccountNumber"));
	long amount = Long.parseLong(request.getParameter("amount"));
	String transfer = request.getParameter("transfer");
	String date = request.getParameter("date");
	String description = request.getParameter("description");
	String senderBankName = request.getParameter("senderBankName");
	String senderName = request.getParameter("senderName");
	FundTransfer fundTransfer = new FundTransfer();
	fundTransfer.setSenderBankName(senderBankName);
	fundTransfer.setSenderName(senderName);
	fundTransfer.setReceiverBankName(receiverBankName);
	fundTransfer.setReceiverName(receiverName);
	fundTransfer.setReceiverAccountNumber(receiverAccountNumber);
	fundTransfer.setSenderAccountNumber(senderAccountNumber);
	fundTransfer.setAmount(amount);
	fundTransfer.setDate(date);
	fundTransfer.setTransfer(transfer);
	fundTransfer.setDescription(description);
	fundTransfer.setPassword(generateOTP.geek_Password(6));
	try {
		fundTransfer.setReferenceNumber(fundTranserDAO.fetchReferenceNumber());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(fundTransfer.getReceiverBankName().equalsIgnoreCase("BAS") && fundTransfer.getSenderBankName().equalsIgnoreCase("BAS")) {
		//first debit  Done
		//check condtion  Done
		//second credit
		AccountDetailsDAO accountDetailsDAO = new AccountDetailsDAO();
		long balance = 40000;
		try {
			balance = accountDetailsDAO.getAccountBalance(fundTransfer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(balance > fundTransfer.getAmount()) {
			//process
			boolean check = fundTranserDAO.insertStatement(fundTransfer);
			if(check) {
				forward = "OTP.jsp";
				sess.setAttribute("OTP", fundTransfer);
			}
			
		}
		else {
			forward = "InsufficientBalance.jsp";
		}
	}
	else if(fundTransfer.getSenderBankName().equalsIgnoreCase("BAS")) {
		//only debit
		AccountDetailsDAO accountDetailsDAO = new AccountDetailsDAO();
		long balance =0;
		try {
			balance = accountDetailsDAO.getAccountBalance(fundTransfer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(balance > fundTransfer.getAmount()) {
			//process
			boolean check = fundTranserDAO.insertStatement(fundTransfer);
			if(check) {
				forward = "OTP.jsp";
				sess.setAttribute("OTP", fundTransfer);
			}
			
		}
		else {
			forward = "InsufficientBalance.jsp";
		}
	}
	
	RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	dispatcher.forward(request, response);
	
	}

}
