package View;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Control;

import Model.Event;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateEvent() {
		super();

	}

	/*******************************************
	 * doGet
	 **********************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CreateEvent.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Getting the session
		HttpSession session = request.getSession(true);
		//Create new event
		Event eventStatus = Control.createEvent(request);
		if (eventStatus != null) {
			//Set the user to become a manager
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/launchEvent.jsp");
		dispatcher.forward(request, response);
	}

}
