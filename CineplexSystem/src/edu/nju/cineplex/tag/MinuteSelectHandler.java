package edu.nju.cineplex.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MinuteSelectHandler extends SimpleTagSupport{
	public void doTag() throws JspException, IOException {
		JspWriter out=getJspContext().getOut();
		out.println("<select>");
		for(int i=0;i<60;i++){
			out.println("<option value='"+i+"'>"+i+"</option>");
		}
		out.println("</select>");
	}

}
