package eLibraryApp.Servlets;

import eLibraryApp.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
    //Line 16 to 40: Servelet returns html page for return book page.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Return Book</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        String callno = req.getParameter("callno");
        String strStudentid = req.getParameter("studentid");
        int studentid=Integer.parseInt(strStudentid);

        int i= BookDao.ReturnBook(callno,studentid);
        if(i>0){
            out.println("<h3>Book returned successfully</h3>");
        }else{
            out.println("<h3>Sorry, unable to return book.</h3><p>We may have shortage of book. Kindly visit later.</p>");
        }
        out.println("</div>");
    }
}
