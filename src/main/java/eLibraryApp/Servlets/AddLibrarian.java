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

@WebServlet("/Addlibrarian")
public class AddLibrarian extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Line 18 to 46: loads the html for the add librarian page
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Add Librarian</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        req.getRequestDispatcher("navadmin.html").include(req, resp);
        out.println("<div class='container'>");

        //Line 33 to 39: extracts four different piece of data to form LibrarianBean object which is saved to DB.
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String smobile = req.getParameter("mobile");
        long mobile = Long.parseLong(smobile);
        LibrarianBean bean=new LibrarianBean(name, email, password, mobile);
        LibrarianDao.Save(bean);
        out.print("<h4>Librarian added successfully</h4>");
        req.getRequestDispatcher("addlibrarianform.html").include(req, resp);


        out.println("</div>");
        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();

    }
}
