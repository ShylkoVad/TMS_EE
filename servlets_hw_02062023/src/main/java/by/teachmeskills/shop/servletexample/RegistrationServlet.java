package by.teachmeskills.shop.servletexample;

import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import by.teachmeskills.shop.utils.ValidatorUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (((User) req.getSession().getAttribute("user")).getEmail().equals("empty")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String birthday = req.getParameter("birthday");
        if (ValidatorUtils.validateRegistration(email, name, surname, password, birthday)) {
            User user = new User(email, password, name, surname, birthday);
            if (user != null) {
                CRUDUtils.saveUser(user);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                req.setAttribute("categories", CRUDUtils.getCategories());
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        }
    }
}
