package View;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Control;
import Model.*;

/**
 * Servlet implementation class JoinEvent
 */
@WebServlet("/JoinEvent")
public class JoinEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/JoinEvent.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String last_invalid = (String)request.getSession().getAttribute("invalid");//we have in the past invalid link so we try again
		if (last_invalid == null)
		{
			Control.UserJoin(request);
		}
		String invalid = Integer.toString(Control.insertSongs(request));
		System.out.println("value of invalid at join servlet: " + invalid);
		// we need to det the user pin number to the event pin number
		if(invalid.equals("0"))
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Wait.jsp");
		dispatcher.include(request, response);
		}
		else//one or more of the links invalid
		{
			request.getSession().setAttribute("invalid", invalid);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/JoinEvent.jsp");
			dispatcher.include(request, response);
		}

	}

}
