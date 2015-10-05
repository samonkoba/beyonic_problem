package com.registration.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyonic.SMS.TwilioSMS;
import com.beyonic.database.User;
import com.twilio.sdk.TwilioRestException;

/**
 * Servlet implementation class resendCode
 */
@WebServlet("/resendCode")
public class resendCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FROM = "(256) 801-2437";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resendCode() {
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
		
		User user = (User) request.getSession().getAttribute("user");
		
		
		if(user != null){
			try {
				TwilioSMS.sendSMS(FROM, user.getPhone(), "you verification code is "+user.getVerification_code());
				request.getSession().setAttribute("message", "Please enter the code sent to the number you provided");
				
				
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block

				request.getSession().setAttribute("message", "There has been a problem sending your code please retry");
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verify_account.jsp");
			dispatcher.forward(request, response);
		}else{

			request.getSession().setAttribute("message", "your session has expired");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
