package edu.nju.cineplex.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.MoviePlanBean;
import edu.nju.cineplex.service.MovieManageService;
import edu.nju.cineplex.service.MovieManageServiceBean;

/**
 * Servlet implementation class WaiterSingleMovie
 */
@WebServlet("/WaiterSingleMovie")
public class WaiterSingleMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MovieManageService movieManageService=MovieManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterSingleMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String movieId=request.getParameter("movieId");
		MoviePlanBean moviePlanBean=movieManageService.getMoviePlanBean(movieId);
		request.setAttribute("moviePlanBean", moviePlanBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/waiterSingleMovie.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
