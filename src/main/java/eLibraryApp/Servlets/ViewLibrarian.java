package eLibraryApp.Servlets;

import eLibraryApp.beans.LibrarianBean;
import eLibraryApp.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewLibrarian")
public class ViewLibrarian extends HttpServlet {
    //Line 18 to 44: gets the html for ViewLibrarian page.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>View Librarian</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        req.getRequestDispatcher("navadmin.html").include(req, resp);
        out.println("<div class='container'>");

        //Line 34: gets list of librarian currently on database(DB).
        List<LibrarianBean> list = LibrarianDao.View();

        out.println("<table class='table table-bordered table-striped'>");
        out.println("<tr><th>Id</th><th>Name</th><th>Email</th><th>Password</th><th>Mobile</th><th>Edit</th><th>Delete</th></tr>");
        //Line 39 to 41: Lists all the librarian data in html so it can be viewed on the view librarian page.
        for(LibrarianBean bean:list){
            out.println("<tr><td>" + bean.getId() +
                    "</td><td>" + bean.getName() +
                    "</td><td>" + bean.getEmail() +
                    "</td><td>" + bean.getPassword() +
                    "</td><td>" + bean.getMobile()+
                    "</td><td><a href='EditLibrarianForm?id=" + bean.getId() +
                    "'>Edit</a></td><td><a href='DeleteLibrarian?id=" + bean.getId() +
                    "'>Delete</a></td></tr>");
        }
        out.println("</table>");

        out.println("</div>");
        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
