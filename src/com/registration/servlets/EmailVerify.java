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
 * Servlet implementation class EmailVerify
 */
@WebServlet("/EmailVerify")
public class EmailVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String  verification_code = request.getParameter("code");
		String  email = request.getParameter("email");
		try {
			User user = DbHelper.getUser(email);
			if(user!=null){
				if(DbHelper.activateUser(user)){
					
					request.getSession().setAttribute("message", "your activation is successfull you may now login");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}else{
					request.getSession().setAttribute("message", "your activation has failed please click on the emailed link again or enter the activation code here");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verify_account.jsp");
					dispatcher.forward(request, response);
					
				}
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
