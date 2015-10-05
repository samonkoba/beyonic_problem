package com.registration.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyonic.database.DbHelper;
import com.beyonic.database.User;

/**
 * Servlet implementation class VerifyCode
 */
@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyCode() {
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
		String  verification_code = request.getParameter("code");
		User user = (User) request.getSession().getAttribute("user");
		
		if(verification_code.equals(user.getVerification_code())){
			try {
				if(DbHelper.activateUser(user)){
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
				dispatcher.forward(request, response);
				}else{
					
					request.getSession().setAttribute("message", "verification failed please retry");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verify_account.jsp");
					dispatcher.forward(request, response);
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			request.getSession().setAttribute("message", "your code isnt correct please resend if you forgot");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verify_account.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
	}

}
