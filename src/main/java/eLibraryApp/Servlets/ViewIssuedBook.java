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
import java.util.List;

@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>View Issued Book</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");

        List<IssueBookBean> list= BookDao.ViewIssuedBooks();

        out.println("<table class='table table-bordered table-striped'>");
        out.println("<tr><th>Callno</th><th>Student Id</th><th>Student Name</th><th>Student Mobile</th><th>Issued Date</th><th>Return Status</th></tr>");
        for(IssueBookBean bean:list){
            out.println("<tr><td>" + bean.getCallNo() +
                    "</td><td>" + bean.getStudentId() +
                    "</td><td>" + bean.getStudentName() +
                    "</td><td>"+bean.getStudentMobile() +
                    "</td><td>"+bean.getIssueDate() +
                    "</td><td>"+bean.getReturnStatus() +
                    "</td></tr>");
        }

        out.println("</table>");

        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();

    }
}
