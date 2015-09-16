package edu.nju.cineplex.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
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
//		String ajaxUpdateResult = "";
		String imageName=request.getServletContext().getRealPath("")+"\\image\\";
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();
		String output = "";
        
        try {

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            

            for (FileItem item : items) {

                if (item.isFormField()) {
                	imageName=imageName+item.getString()+".gif";
//                    ajaxUpdateResult += "Field " + item.getFieldName() + 
//                    " with value: " + item.getString() + " is successfully read\n\r";

                } else {

                    String fileName = item.getName();
                    if(fileName.endsWith(".jpg")
                    		||fileName.endsWith(".JPG")
                    		||fileName.endsWith(".gif")
                    		||fileName.endsWith(".png")
                    		||fileName.endsWith(".PNG")){
                    	InputStream is = item.getInputStream();
                    	OutputStream os=new FileOutputStream(imageName);
                    	byte buffer[] = new byte[8192];
                        int count = 0;
                        while ((count = is.read(buffer)) > 0) {
                            os.write(buffer, 0, count);
                        }
                        os.close();
                        is.close();
                        output="<response><result>1</result></response>";
                    	
                    }else{
                    	output="<response><result>0</result>"
                    			+ "<error>必须是jpg/JPG/gif/png/PNG格式的图片！</error>"
                    			+ "</response>";
                    }
                    // Do whatever with the content InputStream.
//                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
                }

            }

        } catch (FileUploadException e) {

            throw new ServletException("Parsing file upload failed.", e);

        }
        out.println(output);
		out.close();

	}

}
