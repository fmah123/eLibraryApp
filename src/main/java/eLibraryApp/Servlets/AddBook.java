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

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
    //This servlet is for adding
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        //Line 32 to 39: saves data from request
        out.println("<div class='container'>");
        String callno = req.getParameter("callno");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String strQuantity = req.getParameter("quantity");
        int quantity = Integer.parseInt(strQuantity);
        BookBean bean = new BookBean(callno, name, author, publisher, quantity);

        int i = BookDao.Save(bean);
        //Line 43: Checks if the book is saved to DB.
        if(i>0){
            out.println("<h3>Book saved successfully</h3>");
        }

        //Line 47: The getRequestDispatcher method Adds relevant html for add book form page.
        req.getRequestDispatcher("addbookform.html").include(req, resp);
        out.println("</div>");

        //This is included in all the servlet class
        req.getRequestDispatcher("footer.hmtl").include(req, resp);
        out.close();
    }
}
