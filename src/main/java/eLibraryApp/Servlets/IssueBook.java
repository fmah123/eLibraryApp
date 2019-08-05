package eLibraryApp.Servlets;

import eLibraryApp.beans.IssueBookBean;
import eLibraryApp.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Add Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        String callNo=req.getParameter("callno");
        String studentId=req.getParameter("studentid");
        String studentName=req.getParameter("studentname");
        String strStudentMobile=req.getParameter("studentmobile");
        long studentMobile=Long.parseLong(strStudentMobile);

        IssueBookBean bean=new IssueBookBean(callNo,studentId,studentName,studentMobile);
        int i= BookDao.IssueBook(bean);
        if(i>0){
            out.println("<h3>Book issued successfully</h3>");
        }else{
            out.println("<h3>Sorry, unable to issue book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
        }
        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
