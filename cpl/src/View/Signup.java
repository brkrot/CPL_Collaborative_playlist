package View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Control;
import Model.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Signup.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//remove invalid from last user
		String last_invalid = (String)request.getSession().getAttribute("invalid");
		if (last_invalid != null)
		{
			request.getSession().removeAttribute("invalid");
		}
		
		
		if (Control.createUser(request) == 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserInterface.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("error", "email is already exists in the system!");
			System.out.println("email is already exists in the system!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Signup.jsp");
			dispatcher.forward(request, response);
		}
	}

}
