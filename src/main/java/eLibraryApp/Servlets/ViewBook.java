package eLibraryApp.Servlets;

import eLibraryApp.beans.BookBean;
import eLibraryApp.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
    //This servlet will build the html for the view book page for display on website/client computer.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>View Book</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");

        List<BookBean> list= BookDao.View();

        out.println("<table class='table table-bordered table-striped'>");
        out.println("<tr><th>Callno</th><th>Name</th><th>Author</th><th>Publisher</th><th>Quantity</th><th>Issued</th><th>Delete</th></tr>");
        //Line 37 to 46: This for loop will iterate over all the book information stored on db in order to build html to display on the web page.
        for(BookBean bean:list){
            out.println("<tr><td>" + bean.getCallNo() +
                    "</td><td>" + bean.getName() +
                    "</td><td>" + bean.getAuthor() +
                    "</td><td>" + bean.getPublisher() +
                    "</td><td>" + bean.getQuantity() +
                    "</td><td>" + bean.getIssued() +
                    "</td><td><a href='DeleteBook?callno=" + bean.getCallNo() +
                    "'>Delete</a></td></tr>");
        }
        out.println("</table>");

        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
