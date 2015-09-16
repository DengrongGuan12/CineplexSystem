package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.MovieManageService;
import edu.nju.cineplex.service.MovieManageServiceBean;

/**
 * Servlet implementation class AddMovie
 */
@WebServlet("/WaiterAddMovie")
public class WaiterAddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MovieManageService movieManageService=MovieManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterAddMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/AddMovie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();
		String output = "";
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String endTime=request.getParameter("endTime");
		if(movieManageService.addMovie(title, description, endTime)){
			output="<response><result>1</result></response>";
		}else{
			output="<response><result>0</result></response>";
		}
		out.println(output);
		out.close();
	}

}
