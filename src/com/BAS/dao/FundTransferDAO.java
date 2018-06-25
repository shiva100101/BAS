package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BAS.model.FundTransfer;
import com.BAS.model.AccountDetails;
import com.BAS.model.PinCredentials;
import com.BAS.model.RegisterAccount;
import com.BAS.util.DBUtil;

public class FundTransferDAO {

	 
		private Connection connection;

		public FundTransferDAO() {
			connection = DBUtil.getConnection();
			System.out.println("connected" + connection);
		}

		public boolean insertStatement(FundTransfer fundTransfer) {
			boolean flag = false;
			try {
	        System.out.println("NewPinDAO | updatePindUser()| entering method");
	        PreparedStatement ps = connection.prepareStatement("insert into ACCOUNT_STATEMENT(USER_NAME,REF_NUM,TXN_DATE,TXN_DESC,DEBIT) values (?,?,?,?,?)");
			System.out.println("NewPinDAO | checkCredentials| connection"+ connection);
			System.out.println(ps);
			ps.setString(1,fundTransfer.getSenderName());
			ps.setString(2,fundTransfer.getReferenceNumber());
			ps.setString(3,fundTransfer.getDate());
			ps.setString(4,fundTransfer.getDescription());
			ps.setLong(5, fundTransfer.getAmount());
			
	    	 int i =ps.executeUpdate();
	    	
	    	if(i>0) {
	    		System.out.println("transacton successfull");
	    		flag = inserttForReceiver(fundTransfer);
	    		
	    	}
	    	
	    		
	    	
	    	  
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return flag;


		}
		public boolean inserttForReceiver(FundTransfer fundTransfer) {
			boolean flag = false;
			try {
	        System.out.println("NewPinDAO | updatePindUser()| entering method");
	        PreparedStatement ps = connection.prepareStatement("insert into ACCOUNT_STATEMENT(USER_NAME,REF_NUM,TXN_DATE,TXN_DESC,CREDIT) values (?,?,?,?,?)");
			System.out.println("NewPinDAO | checkCredentials| connection"+ connection);
			System.out.println(ps);
			ps.setString(1,fundTransfer.getReceiverName());
			ps.setString(2,fundTransfer.getReferenceNumber());
			ps.setString(3,fundTransfer.getDate());
			ps.setString(4,fundTransfer.getDescription());
			ps.setLong(5, fundTransfer.getAmount());
			
	    	 int i =ps.executeUpdate();
	    	
	    	if(i>0) {
	    		System.out.println("transacton successfull");
	    		flag = true;
	    		
	    	}
	    	
	    		
	    	
	    	  
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return flag;


		}
			
			
		

		public String fetchReferenceNumber() throws SQLException {
			String refNumber = "";
			PreparedStatement statement = connection
					.prepareStatement("select ref_num from account_statement order by ref_num desc limit 1");
			System.out.println("FundTransferDAO | fetchREferenceNumber() | connection"
					+ connection);
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				refNumber = rs.getString("REF_NUM");
				if( refNumber.equalsIgnoreCase(null) ||refNumber.equalsIgnoreCase("") ) {
					System.out.println("FundTransferDAO | fetchREferenceNumber() | reference number is null");
					refNumber  = "TX_1";
				}
				else {
					System.out.println("FundTransferDAO | fetchREferenceNumber() | reference number is  not null" + refNumber);
				      long b = Long.parseLong(refNumber.substring(3));
				      b=b+1;
				      refNumber = refNumber.substring(0,3) + b;
				}

			}

			return refNumber;

		}

		public boolean updatDetails(FundTransfer fundTransfer) {
			boolean flag = false;
			try {
	        System.out.println("NewPinDAO | updateacc()| entering method");
	        PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNT_STATEMENT SET STATUS=? where REF_NUM = ?");
	        ps.setString(1,fundTransfer.getStatus());
	        ps.setString(2,fundTransfer.getReferenceNumber());
	        System.out.println("NewPinDAO | checkCredentials| connection"+ connection);
			System.out.println(ps);
			
			
	    	int i= ps.executeUpdate();
	    	
	    	if(i>0) {
	    		
	    		flag =true;
	    		
	    	}			    	  
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return flag;


		}		
}
