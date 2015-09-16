package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Suggest
 */
@WebServlet("/Suggest")
public class Suggest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList lib=new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Suggest() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(){
    	lib.add("a");
    	lib.add("able");
    	lib.add("access");
    	lib.add("advance");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 设置生成文件的类型和编码格式
	     response.setContentType("text/xml;charset=UTF-8");
	     response.setHeader("Cache-Control","no-cache");
	     PrintWriter out = response.getWriter();
	     String output = "";
	     // 处理接收到的参数
	     String key = request.getParameter("key");
	     if(!key.equals("")){
	    	 ArrayList matchList =getMatchString(key);
		     if (!matchList.isEmpty()) {
		         output += "<response>";
		         for (int i = 0; i < matchList.size();i++) {
		             String match =matchList.get(i).toString();
		             output += "<item>"+ match + "</item>";
		         }
		         output += "</response>";
		      }
	     }
	     
	      out.println(output);
	      out.close();
		
	}
	// 取得所有匹配的字符串
	public ArrayList getMatchString(String key) {
	      ArrayList result = new ArrayList();
	      if (!lib.isEmpty()) {
	         for (int i = 0; i < lib.size(); i++){
	             String str = lib.get(i).toString();
	             if (str.startsWith(key)){
	            	 result.add(str);
	             }
	               
	         }
	      }
	      return result;
	}

}
