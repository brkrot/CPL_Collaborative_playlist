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


@WebServlet("/UserInterface")
public class UserInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInterface() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserInterface.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String join = Control.checkIfEventExist(request);
		if (null == join) {
	
			//User u1 = (User)request.getSession(true).getAttribute("user");		
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/JoinEvent.jsp");
				dispatcher.include(request, response);
			
		} else {
			request.setAttribute("error", join);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserInterface.jsp");
		dispatcher.include(request, response);
		}
		
	}

}
