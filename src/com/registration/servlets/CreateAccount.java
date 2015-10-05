package com.registration.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.SendMailFactory;

import com.beyonic.SMS.TwilioSMS;
import com.beyonic.database.DbHelper;
import com.beyonic.database.User;
import com.twilio.sdk.TwilioRestException;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FROM = "(256) 801-2437";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		String phone = "254"+request.getParameter("phone");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		
		
		//generate verification code for user
		Random randomCode = new Random();
		String verification_code = (100000 + randomCode.nextInt(900000))+"";
		
		
		//insert details to the database
		try {
			
			
			if(DbHelper.insertUser(new User(email, phone, full_name, password, verification_code,false))){
				//user inserted successfully
				
				request.getSession().setAttribute("user_name", full_name);
				request.getSession().setAttribute("phone", phone);
				
				//send email with verification link
				
				
				
				////send sms with verification code
				try {
					TwilioSMS.sendSMS(FROM,phone,"you verification code is "+verification_code);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verify_account.jsp");
				dispatcher.forward(request, response);
				
			}else{
				//user not inserted
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
				dispatcher.forward(request, response);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

	
	
	
	
}
