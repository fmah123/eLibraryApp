package eLibraryApp.Servlets;

import eLibraryApp.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Line 16 to 17: Deletes book via parameter 'callno' and redirects to ViewBook page.
        BookDao.Delete(req.getParameter("callno"));
        resp.sendRedirect("ViewBook");
    }
}
