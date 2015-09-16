package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.MoviePlanBean;
import edu.nju.cineplex.action.business.SimpleMovieBean;
import edu.nju.cineplex.service.MovieManageService;
import edu.nju.cineplex.service.MovieManageServiceBean;

/**
 * Servlet implementation class WaiterAllMovies
 */
@WebServlet("/WaiterAllMovies")
public class WaiterAllMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieManageService movieManageService=MovieManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterAllMovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ArrayList<MoviePlanBean> moviePlanBeans=movieManageService.getMoviePlanBeans();
//		request.setAttribute("allMovies", moviePlanBeans);
		ArrayList<SimpleMovieBean> simpleMovieBeans=movieManageService.getSimpleMovieBeans();
		request.setAttribute("simpleMovieBeans", simpleMovieBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/waiterAllMovies.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
