package View;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Control;
import Model.Event;
import Model.Song;

/**
 * Servlet implementation class TheEvent
 */
@WebServlet("/launchEvent")
public class launchEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public launchEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/launchEvent.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Event e1 = (Event) request.getSession().getAttribute("event");
		if (e1.getUsersConfirmed() < 2) {
			request.setAttribute("Error", "You cannot create a playlist with less than 2 participators");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/launchEvent.jsp");
			dispatcher.include(request, response);
		} else {
			String finalLink = Control.createPlaylist(request);

			int endEvent = Control.endEvent(request);
			request.setAttribute("linkyt", finalLink);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/launchEvent.jsp");
			dispatcher.include(request, response);
		}

	}

}
