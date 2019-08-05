package eLibraryApp.Servlets;

import eLibraryApp.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteLibrarian")
public class DeleteLibrarian extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Line 16 to 19: Deletes a librarian object on DB by id and redirects to the ViewLibrarian servelet code.
        String strID = req.getParameter("id");
        int id = Integer.parseInt(strID);
        LibrarianDao.Delete(id);
        resp.sendRedirect("ViewLibrarian");
    }
}
