package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Refresh
 */
@WebServlet("/Refresh")
public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置生成文件的类型和编码格式
	      response.setContentType("text/xml;charset=UTF-8");
	      response.setHeader("Cache-Control","no-cache");
	      PrintWriter out = response.getWriter();
	      String res = "";
	      String output = "";
	      //处理接收到的参数，生成响应的XML文档
	      Date date = new Date();
	      res += "上次页面刷新时间为:"+date.toLocaleString();
	      output = "<response>"+res+"</response>";
	      out.println(output);
	      out.close();
	}

}
