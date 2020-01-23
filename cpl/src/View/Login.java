package View;

import CPLDB.DB;
import Controller.Control;
import java.io.IOException;
import java.io.PrintWriter;
import Model.*;
import Controller.*;
import CPLDB.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	public Login() {
	}
	/*********************** doGet **********************************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creating the database
		Control.createDB();
		
		// Redirect to the next pages
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

	/***************************************
	 * doPost
	 ************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * checkLogin check the login username and password if succeded return values: 1
		 * for regular user 0 for bad password or -1 for incorrect userName;
		 */
		
		//remove invalid from last user
		String last_invalid = (String)request.getSession().getAttribute("invalid");
		if (last_invalid != null)
		{
			request.getSession().removeAttribute("invalid");
		}
		
		
		System.out.println("Login servlet..");
		RequestDispatcher dispatcher;

		// Get the status of the login
		int logingStatus = Control.checkLogin(request);
		switch (logingStatus) {
		case 3:
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Wait.jsp");
			dispatcher.include(request, response);
			break;
		case 2:
			System.out.println((User)request.getSession(true).getAttribute("admin"));
			System.out.println((Event)request.getSession(true).getAttribute("event"));
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/launchEvent.jsp");
			dispatcher.include(request, response);
			break;
		case 1:
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserInterface.jsp");
			dispatcher.include(request, response);
			break;
		case 0:
			request.setAttribute("error", "Invalid password");
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
			dispatcher.include(request, response);
			break;
		case -1:
			request.setAttribute("error", "Invalid email, try to re-enter or sign up");
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
			dispatcher.include(request, response);
			break;
		default:		
		}
	}

}
