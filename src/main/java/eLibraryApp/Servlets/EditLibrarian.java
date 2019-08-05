package eLibraryApp.Servlets;

import eLibraryApp.beans.LibrarianBean;
import eLibraryApp.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditLibrarian")
public class EditLibrarian extends HttpServlet {
    //Line 16 to 26: does post request in order to update a particular librarian details on DB.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId =req.getParameter("id");
        int id = Integer.parseInt(strId);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String strMobile = req.getParameter("mobile");
        long mobile = Long.parseLong(strMobile);
        LibrarianBean bean = new LibrarianBean(id, name, email, password, mobile);
        LibrarianDao.Update(bean);
        resp.sendRedirect("ViewLibrarian");
    }
}
