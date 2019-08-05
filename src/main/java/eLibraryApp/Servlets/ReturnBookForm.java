package eLibraryApp.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReturnBookForm")
public class ReturnBookForm extends HttpServlet {
    //Line 14 to 33: Builds html page for the return book form .
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Return Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        //Line 26 and 29: The addition of these html file is needed to complete the return book form page.
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        req.getRequestDispatcher("returnbookform.html").include(req, resp);
        out.println("</div>");



        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
